package com.mysocialfeed;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class SignInServlet extends HttpServlet {
	static { // Fait connaître les classes-entités à Objectify
		ObjectifyService.register(User.class);
		ObjectifyService.register(Account.class);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("username") == null) {
			this.getServletContext().getRequestDispatcher("/JSP/SignIn.jsp")
					.forward(request, response);
		} else {
			response.sendRedirect("/ServerServlet");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println(request.getParameter("username"));
		if (session.getAttribute("username") == null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username == null || password == null) {
				this.getServletContext()
						.getRequestDispatcher("/JSP/SignIn.jsp")
						.forward(request, response);
			} else {
				User user = ObjectifyService.ofy().load().type(User.class)
						.filter("username ", username).first().now();
				if (user.getPassword().equals(password)) {
					session.setAttribute("user", user);
					Key<User> keyUser = Key.create(user);
					Account account = ofy().load().type(Account.class)
							.ancestor(keyUser).first().now();
					session.setAttribute("account", account);
					// List<Account> accounts =
					// ObjectifyService.ofy().load().type(Account.class).ancestor(keyUser).list();
					// session.setAttribute("accounts", accounts);
					response.sendRedirect("/ServerServlet");
				} else {
					this.getServletContext()
							.getRequestDispatcher("/JSP/SignIn.jsp")
							.forward(request, response);
				}

			}
		}

	}
}