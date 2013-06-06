/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.interfaces;

/**
 *
 * @author Vincent
 */
public interface UserService {
    public boolean isAuthenticated();
    
    // User Operations
    public boolean authenticate(String userName, String passWord);
    public void userSignOff();
    //UserData authenticatedData {get ;}
}
