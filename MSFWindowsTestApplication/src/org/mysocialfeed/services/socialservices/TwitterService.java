/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.socialservices;

import twitter4j.auth.AccessToken;

/**
 *
 * @author Vincent
 */
public interface TwitterService {
    
    public String setUpAuthentification();
    public boolean authenticate();
    public void setPin(final String pin);
    public AccessToken getAccessToek();
}
