package com.mysocialfeed;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class PostTwitter {


	public static String PostToTwitter(Account account, String message, User user) {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(account.getToken(),
				account.getTokenSecret());
		Twitter twitter = factory.getInstance(accessToken);
		try {
			twitter.updateStatus(message);
			message = "Le message \"<strong>" + message + "</strong>\" à bien été envoyé";
			return message;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Impossible d'envoyer le message";
		}
	}
}
