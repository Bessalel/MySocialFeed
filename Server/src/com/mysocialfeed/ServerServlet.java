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
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.*;

@SuppressWarnings("serial")
public class ServerServlet extends HttpServlet {
	static { // Fait connaître les classes-entités à Objectify
		ObjectifyService.register(User.class);
		ObjectifyService.register(Account.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		List<User> users =  ObjectifyService.ofy().load().type(User.class).list();
		
		request.setAttribute("users", users);
		this.getServletContext().getRequestDispatcher("/JSP/Home.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

//
//Twitter twitter = TwitterFactory.getSingleton();
//RequestToken requestToken;
//try {
//	requestToken = twitter.getOAuthRequestToken();
//	String authUrl = requestToken.getAuthorizationURL();
//	request.setAttribute("authUrl", authUrl);
//	
//} catch (TwitterException e) {
//	e.printStackTrace();
//}
//AccessToken accessToken = null;
