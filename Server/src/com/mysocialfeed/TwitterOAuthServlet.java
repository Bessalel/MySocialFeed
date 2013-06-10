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
//		if (request.getParameter("oauth_token") == null
//				&& request.getParameter("oauth_verifier") == null) {
			Twitter twitter = TwitterFactory.getSingleton();
			try {
				RequestToken requestToken = twitter.getOAuthRequestToken();
				AccessToken accessToken = null;
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				while (null == accessToken) {
					System.out
							.println("Open the following URL and grant access to your account:");
					System.out.println(requestToken.getAuthorizationURL());
					System.out
							.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
					String pin = br.readLine();
					try {
						if (pin.length() > 0) {
							accessToken = twitter.getOAuthAccessToken(
									requestToken, pin);
						} else {
							accessToken = twitter.getOAuthAccessToken();
						}
						
					} catch (TwitterException te) {
						if (401 == te.getStatusCode()) {
							System.out
									.println("Unable to get the access token.");
						} else {
							te.printStackTrace();
						}
					}
				}
				
				Status status;
				String name = twitter.verifyCredentials().getName();
				System.out.println("Welcome " + name);
				try {
					status = twitter.updateStatus("Hello from T4J x2");
					System.out.println("Successfully updated the status to ["
							+ status.getText() + "].");
					
				} catch (TwitterException te) {
					if(403 == te.getStatusCode()){
						System.out.println("You have already posted this message.");
					}
				}
				List<Status> statuses = twitter.getHomeTimeline();
			    System.out.println("Showing home timeline.");
			    for (Status status1 : statuses) {
			        System.out.println(status1.getUser().getName() + ":" +
			                           status1.getText());
			    }
				
				
				String authUrl = requestToken.getAuthorizationURL();
				request.setAttribute("authUrl", authUrl);

			} catch (TwitterException te) {
				te.printStackTrace();
			}

//		} else {
//			Twitter twitter = TwitterFactory.getSingleton();
//			Status status;
//			request.setAttribute("connectedToTwitter", 1);
//			request.setAttribute("yop", request.getParameter("oauth_token"));
//			request.setAttribute("yop2", request.getParameter("oauth_verifier"));
//			System.out.println(request.getParameter("oauth_token"));
//			System.out.println(request.getParameter("oauth_verifier"));
//
//			AccessToken accessToken = new AccessToken(
//					request.getParameter("oauth_token"),
//					request.getParameter("oauth_verifier"));
//			try {
//				twitter.setOAuthAccessToken(accessToken);
//				status = twitter.updateStatus("Hello from T4J");
//				System.out.println("Successfully updated the status to ["
//						+ status.getText() + "].");
//				// twitter.getOAuthAccessToken(
//				// request.getParameter("oauth_token"),
//				// request.getParameter("oauth_verifier"));
//				// request.setAttribute("yop",
//				// request.getParameter("oauth_token"));
//			} catch (TwitterException e) {
//				e.printStackTrace();
//			}
//		}

//		this.getServletContext().getRequestDispatcher("/JSP/TwitterOAuth.jsp")
//				.forward(request, response);

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
//http://127.0.0.1:8081/TwitterOAuthServlet