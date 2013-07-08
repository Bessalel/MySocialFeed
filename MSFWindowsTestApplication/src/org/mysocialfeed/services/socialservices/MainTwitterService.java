/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.socialservices;

import com.google.inject.Inject;
import org.mysocialfeed.services.interfaces.SocialAccountService;
import org.mysocialfeed.services.repository.UserDataService;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Vincent
 */
public class MainTwitterService implements TwitterService {
    
    private final ConfigurationBuilder cb = new ConfigurationBuilder();
    
    private final TwitterFactory tf;
    private final Twitter twitter;
    
    private RequestToken requestToken;
    private AccessToken accessToken = null;
    
    private String pin;
    
    private final SocialAccountService socialAccountService;
    private final UserDataService userDataService;
    
    @Inject
    public MainTwitterService(SocialAccountService socialAccountService, UserDataService userDataService) {
        this.socialAccountService = socialAccountService;
        this.userDataService = userDataService;
        
        this.cb.setDebugEnabled(true);
        this.cb.setOAuthConsumerKey("u7P0tV8EfS9xYrlQBM1JQ");
        this.cb.setOAuthConsumerSecret("OysElIcFmFpV9RmKB3b6XPB8yZ8GMpRXQWQuNWnbmI");
        
        
        this.tf = new TwitterFactory();
        this.twitter = tf.getInstance();
        this.twitter.setOAuthConsumer("u7P0tV8EfS9xYrlQBM1JQ", "OysElIcFmFpV9RmKB3b6XPB8yZ8GMpRXQWQuNWnbmI");
    }
    
    @Override
    public String setUpAuthentication() {
         try {
            this.requestToken = this.twitter.getOAuthRequestToken();
            return this.requestToken.getAuthorizationURL();
        } catch (TwitterException te) {
            te.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean authenticate() {
       try {
            System.out.println(this.requestToken.getToken() + "\n" + this.requestToken.getTokenSecret());
            System.out.println(this.pin);
            this.accessToken = this.twitter.getOAuthAccessToken(this.requestToken, this.pin);
            System.out.println("token = " + this.accessToken.getToken());
            System.out.println("secret token = " + this.accessToken.getTokenSecret());
//            this.socialAccountService.insertNewAccountIntoDatabase(
//                    this.userDataService.getUserFirstName(), this.userDataService.getUserLastName(), 
//                    this.accessToken.getToken(), this.accessToken.getTokenSecret(), this.userDataService.getUserEmailAddress(), "twitter");

       } catch (TwitterException te) {
           te.printStackTrace();
           return false;
       }
        return true;
    }
    
    @Override
    public void setPin(final String pin) {
        this.pin = pin;
    }
    
    @Override
    public AccessToken getAccessToek() {
        return this.accessToken;
    }
}
