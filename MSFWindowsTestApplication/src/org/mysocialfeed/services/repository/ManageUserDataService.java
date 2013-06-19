/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;

/**
 *
 * @author Vincent
 */
public class ManageUserDataService implements UserDataService {
    // user data
    private int userID;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmailAddress;
    private int nbFacebook;
    private int nbTwitter;
    private int nbGooglePlus;
    private int nbPinterest;


    @Override
    public void loadUserData(
            final int userID, final String userName, final String userFirstName,
            final String userLastName, final String userEmailAddress, final int nbFacebook, 
            final int nbTwitter, final int nbGooglePlus, final int nbPinterest) {
        
        this.userID = userID;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailAddress = userEmailAddress;
        this.nbFacebook = nbFacebook;
        this.nbTwitter = nbTwitter;
        this.nbGooglePlus = nbGooglePlus;
        this.nbPinterest = nbPinterest;
    }
    
    @Override
    public void unloadAllUserData(){
        this.userID = 0;
        this.userName = null;
        this.userFirstName = null;
        this.userLastName = null;
        this.userEmailAddress = null;
        this.nbFacebook = 0;
        this.nbGooglePlus = 0;
        this.nbPinterest = 0;
        this.nbTwitter = 0;
    }
    
    // Get user social account(s) status
    @Override
    public boolean hasAccount() {
        return (this.nbFacebook + this.nbGooglePlus + this.nbPinterest + this.nbTwitter) > 0;
    }

    @Override
    public boolean hasFacebook() {
        return this.nbFacebook > 0;
    }

    @Override
    public boolean hasGooglePlus() {
        return this.nbGooglePlus > 0;
    }

    @Override
    public boolean hasPinterest() {
        return this.nbPinterest > 0;
    }

    @Override
    public boolean hasTwitter() {
        return this.nbTwitter > 0;
    }

    
    // Get user social account(s) number
    @Override
    public int getNbFbAccount() {
        return this.nbFacebook;
    }

    @Override
    public int getNbGpAccount() {
        return this.nbGooglePlus;
    }

    @Override
    public int getNbPnAccount() {
        return this.nbPinterest;
    }

    @Override
    public int getNbTwAccount() {
        return this.nbTwitter;
    }

    
    // Get user infos
    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getUserFirstName() {
        return userFirstName;
    }

    @Override
    public String getUserLastName() {
        return userLastName;
    }

    @Override
    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    
    // update user infos & social account(s)
    @Override
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @Override
    public void setUserFirstName(final String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Override
    public void setUserLastName(final String userLastName) {
        this.userLastName = userLastName;
    }

    @Override
    public void setUserEmailAddress(final String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    @Override
    public void setNbFacebook(final int nbFacebook) {
        this.nbFacebook = nbFacebook;
    }

    @Override
    public void setNbTwitter(final int nbTwitter) {
        this.nbTwitter = nbTwitter;
    }

    @Override
    public void setNbGooglePlus(final int nbGooglePlus) {
        this.nbGooglePlus = nbGooglePlus;
    }

    @Override
    public void setNbPinterest(final int nbPinterest) {
        this.nbPinterest = nbPinterest;
    }
}
