package com.mysocialfeed;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.*;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import com.googlecode.objectify.annotation.*;

@SuppressWarnings("serial")
public class ServerServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int test = 2;
		request.setAttribute("test", test);
		this.getServletContext().getRequestDispatcher("/JSP/Home.jsp")
				.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}



//response.setContentType("text/html");
//response.setCharacterEncoding( "UTF-8" );
//PrintWriter out = response.getWriter();
//out.println("<!DOCTYPE html>");
//out.println("<html>");
//out.println("<head>");
//out.println("<meta charset=\"utf-8\" />");
//out.println("<title>Test</title>");
//out.println("</head>");
//out.println("<body>");
//out.println("<p>Ceci est une page générée depuis une servlet.</p>");
//out.println("</body>");
//out.println("</html>");
