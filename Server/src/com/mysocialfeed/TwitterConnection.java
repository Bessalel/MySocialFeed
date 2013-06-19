package com.mysocialfeed;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.googlecode.objectify.Key;

public class TwitterConnection {

	public static String CreateConnection(User user, String accountName)
			throws IOException {
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
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
						accessToken = twitter.getOAuthAccessToken(requestToken,
								pin);
					} else {
						accessToken = twitter.getOAuthAccessToken();
					}

				} catch (TwitterException te) {
					if (401 == te.getStatusCode()) {
						return "Unable to get the access token.";
					} else {
						te.printStackTrace();
					}
				}
			}

			return storeAccessToken(user.getUsername(), twitter
					.verifyCredentials().getId(), accountName, accessToken);

			// Key<User> keyUser2 = Key.create(User.class, user.getId());
			// Account account = ofy().load().type(Account.class)
			// .ancestor(keyUser2).first().now();
			// System.out.println("Entrez un message à poster :");
			// String message = br.readLine();
			// PostTwitter post = new PostTwitter();
			// System.out.println(post.PostToTwitter(message,
			// account.getAccountName(), user));

		} catch (TwitterException te) {
			te.printStackTrace();
			return "Erreur";
		}

	}

	private static String storeAccessToken(String username, long id,
			String accountName, AccessToken accessToken) {
		User user = ofy().load().type(User.class).filter("username ", username)
				.first().now();
		Key<User> keyUser = Key.create(User.class, user.getId());
		if (ofy().load().type(Account.class).ancestor(keyUser)
				.filter("accountName", accountName).first().now() != null) {
			return "Le nom de compte existe déja.";
		} else if (ofy().load().type(Account.class).ancestor(keyUser)
				.filter("token", accessToken.getToken()).first().now() != null) {
			return "Ce compte Twitter est déja enregistré sous un autre nom.";
		} else {
			Account account = new Account(keyUser, "Twitter", accountName,
					accessToken.getToken(), accessToken.getTokenSecret());
			ofy().save().entity(account).now();

			List<Account> accounts = ofy().load().type(Account.class)
					.ancestor(keyUser).list();
			for (Account accountInList : accounts) {
				System.out.println(accountInList.getAccountName()
						+ " " + accountInList.getAccountType()
						+ " " + accountInList.getToken()
						+ " " + accountInList.getTokenSecret()
						+ " " + accountInList.getUser());
			}
			return "Le compte " + accountName + " à bien été enregistré.";
		}

	}
}