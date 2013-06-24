package com.mysocialfeed;

import static com.googlecode.objectify.ObjectifyService.ofy;
import java.util.List;
import twitter4j.auth.AccessToken;
import com.googlecode.objectify.Key;

public class StoreAccessToken {
	public static String storeTwitterAccessToken(User user, String accountName,
			AccessToken accessToken) {
		Key<User> keyUser = Key.create(User.class, user.getId());
		if (ofy().load().type(Account.class).ancestor(keyUser)
				.filter("token", accessToken.getToken()).first().now() != null) {
			return "Ce compte Twitter est déja enregistré sous un autre nom.";
		} else {
			Account account = new Account(keyUser, "Twitter", accountName,
					accessToken.getToken(), accessToken.getTokenSecret());
			ofy().save().entity(account).now();
			List<Account> accounts = ofy().load().type(Account.class)
					.ancestor(keyUser).list();
			for (Account accountInList : accounts) {
				System.out.println(accountInList.getAccountName() + " "
						+ accountInList.getAccountType() + " "
						+ accountInList.getToken() + " "
						+ accountInList.getTokenSecret() + " "
						+ accountInList.getUser());
			}
			return "Le compte " + accountName + " à bien été enregistré.";
		}

	}
}