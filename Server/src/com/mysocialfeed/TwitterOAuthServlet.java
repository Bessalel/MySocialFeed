package com.mysocialfeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.impl.translate.CreateContext;
import com.mysocialfeed.PostTwitter;

@SuppressWarnings("serial")
public class TwitterOAuthServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("user") == null) {
			System.out.println("pas connecté");
			response.sendRedirect("/SignInServlet");
		} else {
			User user = (User) session.getAttribute("user");
			Account account = (Account) session.getAttribute("account");
			if (session.getAttribute("account") != null) {
				request.setAttribute("statuses", getTimeline(account));
			}
			// System.out.println("You have the following existing accounts :");
			// List<Account> accounts = (List<Account>)
			// session.getAttribute("accounts");
			// for (Account account : accounts) {
			// System.out
			// .println(account.getAccountType() + " : "
			// + account.getAccountName() + " de "
			// + account.getUser());
			// }
		}
		this.getServletContext().getRequestDispatcher("/JSP/TwitterOAuth.jsp")
				.forward(request, response);
	}
	
	private List<Status> getTimeline(Account account){
		List<Status> statuses = GetTwitterTimeline.GetTimeline(account);
		return statuses;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		Account account = (Account) session.getAttribute("account");
		String accountName = (String) request.getParameter("accountName");
		String status = (String) request.getParameter("status");

		if (accountName != null) {
			System.out.println(accountName);
			String createConnection = TwitterConnection.CreateConnection(user,
					accountName);
			System.out.println(createConnection);
			request.setAttribute("createConnection", createConnection);
		}
		if (status != null) {
			String messagePosted = PostTwitter.PostToTwitter(account, status,
					user);
			request.setAttribute("messagePosted", messagePosted);
		}
		
		if (session.getAttribute("account") != null) {
			request.setAttribute("statuses", getTimeline(account));
		}
		
		
		this.getServletContext().getRequestDispatcher("/JSP/TwitterOAuth.jsp")
				.forward(request, response);
	}
}