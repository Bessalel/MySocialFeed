package com.mysocialfeed;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.TwitterException;

import com.googlecode.objectify.Key;
import static com.googlecode.objectify.ObjectifyService.ofy;
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
			Key<User> keyUser = Key.create(User.class, user.getId());
			RequestToken requestToken = (RequestToken) session
					.getAttribute("requestToken");
			if (request.getParameter("oauth_token") != null
					&& request.getParameter("oauth_verifier") != null) {
				TwitterFactory factory = new TwitterFactory();
				Twitter twitter = factory.getInstance();
				AccessToken accessToken;
				try {
					accessToken = twitter.getOAuthAccessToken(requestToken,
							request.getParameter("oauth_verifier"));
					twitter.setOAuthAccessToken(accessToken);
					String createConnection = StoreAccessToken
							.storeTwitterAccessToken(user, (String) session
									.getAttribute("accountName"), accessToken, twitter.getScreenName());
					request.setAttribute("createConnection", createConnection);
				} catch (TwitterException e1) {
					e1.printStackTrace();
				}

			}

			List<Account> accounts = ofy().load().type(Account.class)
					.ancestor(keyUser).list();
			ArrayList<Account> arrayAccounts = new ArrayList<>(accounts);
			if (accounts != null) {
				session.setAttribute("accounts", arrayAccounts);
			}

			Account account = ofy().load().type(Account.class)
					.ancestor(keyUser).first().now();

			if (account != null) {
				System.out.println("le compte est : "
						+ account.getAccountName());
				session.setAttribute("account", account);
				List<Status> statuses = GetTwitterTimeline.GetTimeline(account);
				request.setAttribute("statuses", statuses);
			}
		}
		this.getServletContext().getRequestDispatcher("/JSP/TwitterOAuth.jsp")
				.forward(request, response);
	}

	private List<Status> getTimeline(Account account) {
		List<Status> statuses = GetTwitterTimeline.GetTimeline(account);
		return statuses;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		String accountName = (String) request.getParameter("accountName");
		String status = (String) request.getParameter("status");
		Key<User> keyUser = Key.create(User.class, user.getId());
		if (accountName != null) {
			if (ofy().load().type(Account.class).ancestor(keyUser)
					.filter("accountName", accountName).first().now() != null) {
				request.setAttribute("createConnection",
						"Ce nom de compte existe deja !");
			} else {
				System.out.println(accountName);
				TwitterFactory factory = new TwitterFactory();
				Twitter twitter = factory.getInstance();
				try {
					RequestToken requestToken = twitter.getOAuthRequestToken();
					session.setAttribute("accountName", accountName);
					session.setAttribute("requestToken", requestToken);
					response.sendRedirect(requestToken.getAuthorizationURL());
				} catch (TwitterException te) {
					te.printStackTrace();
				}
			}
		}
		Account account = ofy().load().type(Account.class).ancestor(keyUser)
				.first().now();
		if (status != null && account != null) {
			String messagePosted = PostTwitter.PostToTwitter(account, status,
					user);
			request.setAttribute("messagePosted", messagePosted);
		}
		if (account != null) {
			request.setAttribute("statuses", getTimeline(account));
			System.out.println("le compte est : " + account.getAccountName());
			session.setAttribute("account", account);
		}
		this.getServletContext().getRequestDispatcher("/JSP/TwitterOAuth.jsp")
				.forward(request, response);
	}
}