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
    private final static Context instance = new Context();

    public static String currentUser = null;
    
    public static Context getInstance() {
        return instance;
    }
    
}
