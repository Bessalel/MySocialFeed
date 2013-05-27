/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.models;

/**
 *
 * @author Windows
 */
public class Context {
    //private final static Context instance = new Context();

    private static UserData currentUser = null;
    private static UserPosts currentPosts = null;
    
    public static void setCurrentUser(UserData currentUser) {
        Context.currentUser = currentUser;
    }

    public static UserData getCurrentUser() {
        return Context.currentUser;
    }

    public static UserPosts getCurrentPosts() {
        return currentPosts;
    }

    public static void setCurrentPosts(UserPosts currentPosts) {
        Context.currentPosts = currentPosts;
    }
}
