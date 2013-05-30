/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.supportingfiles;

/**
 *
 * @author Vincent
 */
public interface UserService {
    public boolean getIsAuthenticated();
    public boolean authenticate(String userName, String passWord);
    public void userSignOff();
    //UserData authenticatedData {get ;}
}
