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
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;
import com.googlecode.objectify.annotation.*;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

@SuppressWarnings("serial")
public class TwitterOAuthServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("[cARhnMxZ6HHZsdNRzI1SWQ]", "[UZloxdl2NtxLLAzDXZ2kRpPsFYkRNWQruyllVEFIXi0]");
		RequestToken requestToken;
		try {
			requestToken = twitter.getOAuthRequestToken();
			String authUrl = requestToken.getAuthorizationURL();

			AccessToken accessToken = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			while (null == accessToken) {
				request.setAttribute("authUrl", authUrl);
				System.out
						.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
				String pin = br.readLine();
				try {
					if (pin.length() > 0) {
						accessToken = twitter.getOAuthAccessToken(requestToken,
								pin);
					} else {
						accessToken = twitter.getOAuthAccessToken();
					}
				} catch (TwitterException te) {
					if (401 == te.getStatusCode()) {
						System.out.println("Unable to get the access token.");
					} else {
						te.printStackTrace();
					}
				}
			}
		} catch (TwitterException te) {
			te.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/JSP/TwitterOAuth.jsp")
				.forward(request, response);

	}
}
