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
     public boolean insertNewUserIntoDatabase(final String userName, final String password,
            final String firstName, final String lastName, final String emailAddr);
    public boolean authenticate(final String userName, final String passWord);
    public void userSignOff();
    //UserData authenticatedData {get ;}
}
