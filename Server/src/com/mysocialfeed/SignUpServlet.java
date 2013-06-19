package com.mysocialfeed;

import com.googlecode.objectify.ObjectifyService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SignUpServlet extends HttpServlet {
	static { // Fait connaître les classes-entités à Objectify
		ObjectifyService.register(User.class);
		ObjectifyService.register(Account.class);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("user") == null) {
			this.getServletContext().getRequestDispatcher("/JSP/SignUp.jsp")
					.forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/ServerServlet")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("user") == null) {
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			User user = new User(username, email, password);
			ObjectifyService.ofy().save().entity(user).now();

			session.setAttribute("user", user);

			response.sendRedirect("/ServerServlet");
		}
	}
}
