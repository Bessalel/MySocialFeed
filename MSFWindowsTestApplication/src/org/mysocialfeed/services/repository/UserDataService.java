/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;


/**
 *
 * @author Vincent
 */
public interface UserDataService {
    // Equivalent of a constructor when first use
    public void loadUserData(
            final int userID, final String userName, final String userFirstName,
            final String userLastName, final String userEmailAddress, final int nbFacebook, 
            final int nbTwitter, final int nbGooglePlus, final int nbPinterest);
    
    // To remove all data from current user
    public void unloadAllUserData();
    
    // User Infos
    public int getUserID();
    public String getUserName();
    public String getUserFirstName();
    public String getUserLastName();
    public String getUserEmailAddress();
    
    // User Social Account(s) status
    public boolean hasAccount();
    public boolean hasFacebook();
    public boolean hasGooglePlus();
    public boolean hasPinterest();
    public boolean hasTwitter();
    
    // User Social Account(s)
    public int getNbFbAccount();
    public int getNbGpAccount();
    public int getNbPnAccount();
    public int getNbTwAccount();
    
    // Update user infos & social account(s)
    public void setUserName(final String userName);
    public void setUserFirstName(final String userFirstName);
    public void setUserLastName(final String userLastName);
    public void setUserEmailAddress(final String userEmailAddress);
    public void setNbFacebook(final int nbFacebook);
    public void setNbTwitter(final int nbTwitter);
    public void setNbGooglePlus(final int nbGooglePlus);;
    public void setNbPinterest(final int nbPinterest);
}
