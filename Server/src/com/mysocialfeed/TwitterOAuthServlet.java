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
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;
import com.googlecode.objectify.annotation.*;

@SuppressWarnings("serial")
public class TwitterOAuthServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (request.getParameter("oauth_token") == null
				&& request.getParameter("oauth_verifier") == null) {
			Twitter twitter = TwitterFactory.getSingleton();
			try {
				RequestToken requestToken = twitter.getOAuthRequestToken("http://127.0.0.1:8081/TwitterOAuthServlet");
				AccessToken accessToken = null;
				String authUrl = requestToken.getAuthorizationURL();
				request.setAttribute("authUrl", authUrl);

			} catch (TwitterException te) {
				te.printStackTrace();
			}

		} 
		else {
			Twitter twitter = TwitterFactory.getSingleton();
			Status status;
			try {
				AccessToken accessToken = twitter.getOAuthAccessToken();
				twitter.setOAuthAccessToken(accessToken);
				status = twitter.updateStatus("Hello from T4J");
				System.out.println("Successfully updated the status to ["
						+ status.getText() + "].");
				// twitter.getOAuthAccessToken(
				// request.getParameter("oauth_token"),
				// request.getParameter("oauth_verifier"));
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.getServletContext().getRequestDispatcher("/JSP/TwitterOAuth.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

// twitter.setOAuthConsumer("[consumer key]", "[consumer secret]");
// twitter.setOAuthConsumer("[cARhnMxZ6HHZsdNRzI1SWQ]",
// "[UZloxdl2NtxLLAzDXZ2kRpPsFYkRNWQruyllVEFIXi0]");
// "http://projetmsf.appspot.com/TwitterOAuthServlet"