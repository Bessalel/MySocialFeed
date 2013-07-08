/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.interfaces;

/**
 *
 * @author Vincent
 */

public interface SocialAccountService {
    public boolean insertNewAccountIntoDatabase(final String firstName, 
            final String lastName, final String emailAddr, 
            final String token, final String tokenSecret, final String table);
}
