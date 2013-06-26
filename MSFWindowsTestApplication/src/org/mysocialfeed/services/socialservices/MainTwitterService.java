/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.socialservices;

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
    
    public MainTwitterService() {
        this.cb.setDebugEnabled(true);
        this.cb.setOAuthConsumerKey("cARhnMxZ6HHZsdNRzI1SWQ");
        this.cb.setOAuthConsumerSecret("UZloxdl2NtxLLAzDXZ2kRpPsFYkRNWQruyllVEFIXi0");
        
        this.tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }
    
    @Override
    public String setUpAuthentification() {
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
            this.accessToken = this.twitter.getOAuthAccessToken(this.requestToken, this.pin);
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
