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
            int userID, String userName, String userFirstName,
            String userLastName, String userEmailAddress, int nbFacebook, 
            int nbTwitter, int nbGooglePlus, int nbPinterest);
    
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
    public void setUserName(String userName);
    public void setUserFirstName(String userFirstName);
    public void setUserLastName(String userLastName);
    public void setUserEmailAddress(String userEmailAddress);
    public void setNbFacebook(int nbFacebook);
    public void setNbTwitter(int nbTwitter);
    public void setNbGooglePlus(int nbGooglePlus);;
    public void setNbPinterest(int nbPinterest);
}
