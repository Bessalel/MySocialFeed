package com.mysocialfeed;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class GetTwitterTimeline {
	public static List<Status> GetTimeline(Account account ){
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(account.getToken(), account.getTokenSecret());
		Twitter twitter = factory.getInstance(accessToken);
	    try {
			List<Status> statuses = twitter.getHomeTimeline();
			return statuses;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
