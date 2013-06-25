package com.mysocialfeed;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class AccountsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("user") == null) {
			System.out.println("pas connecté");
			response.sendRedirect("/SignInServlet");
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Account> accounts = (ArrayList<Account>) session
					.getAttribute("accounts");
			if (accounts != null) {
				Map<String, List<Object>> instancesMap = new HashMap<String, List<Object>>();
				for (Account account : accounts) {
					if (account.getAccountType().equals("Twitter")) {
						Twitter twitter = GetTwitterInstance(account);
						List<Object> listTwitter = instancesMap.get("Twitter");
						if(listTwitter != null){
							listTwitter.add(twitter);
						}else{
							List<Object> newListTwitter = new ArrayList<Object>();
							newListTwitter.add(twitter);
							instancesMap.put("Twitter", newListTwitter);
						}
					}
					request.setAttribute("instancesMap", instancesMap);
					System.out.println(account.getAccountName());
				}
			} else
				System.out.println("pas de compte");
			this.getServletContext().getRequestDispatcher("/JSP/Accounts.jsp")
					.forward(request, response);
		}
	}

	private Twitter GetTwitterInstance(Account account) {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(account.getToken(),
				account.getTokenSecret());
		Twitter twitter = factory.getInstance(accessToken);
		return twitter;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
