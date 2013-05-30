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
    private boolean hasFacebook;
    private int nbFacebook;
    private boolean hasTwitter;
    private int nbTwitter;
    private boolean hasGooglePlus;
    private int nbGooglePlus;
    private boolean hasPinterest;
    private int nbPinterest;

    @Override
    public void loadUserData(
            int userID, String userName, String userFirstName,
            String userLastName, String userEmailAddress, int nbFacebook, 
            int nbTwitter, int nbGooglePlus, int nbPinterest) {
        
        this.userID = userID;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailAddress = userEmailAddress;
        if (nbFacebook == 0) {
            this.hasFacebook = false;
            this.nbFacebook = 0;
        } else {
            this.hasFacebook = true;
            this.nbFacebook = nbFacebook;
        }
        if (nbTwitter == 0) {
            this.hasTwitter = false;
            this.nbTwitter = 0;
        } else {
            this.hasTwitter = true;
            this.nbTwitter = nbTwitter;
        }
        if (nbGooglePlus == 0) {
            this.hasGooglePlus = false;
            this.nbFacebook = 0;
        } else {
            this.hasGooglePlus = true;
            this.nbGooglePlus = nbGooglePlus;
        }
        if (nbPinterest == 0) {
            this.hasPinterest = false;
            this.hasPinterest = true;
        } else {
            this.hasPinterest = true;
            this.nbPinterest = nbPinterest;
        }
    }
    
    @Override
    public void unloadAllUserData(){
        this.userID = 0;
        this.userName = null;
        this.userFirstName = null;
        this.userLastName = null;
        this.userEmailAddress = null;
        this.hasFacebook = false;
        this.hasGooglePlus = false;
        this.hasPinterest = false;
        this.hasTwitter = false;
        this.nbFacebook = 0;
        this.nbGooglePlus = 0;
        this.nbPinterest = 0;
        this.nbTwitter = 0;
    }
    
    // Get user social account(s) status
    @Override
    public boolean hasAccount() {
        if (this.hasFacebook == false && this.hasGooglePlus == false && this.hasPinterest == false && this.hasTwitter == false) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasFacebook() {
        return this.hasTwitter;
    }

    @Override
    public boolean hasGooglePlus() {
        return this.hasGooglePlus;
    }

    @Override
    public boolean hasPinterest() {
        return this.hasPinterest;
    }

    @Override
    public boolean hasTwitter() {
        return this.hasTwitter;
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
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Override
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Override
    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    @Override
    public void setHasFacebook(boolean hasFacebook) {
        this.hasFacebook = hasFacebook;
    }

    @Override
    public void setNbFacebook(int nbFacebook) {
        this.nbFacebook = nbFacebook;
    }

    @Override
    public void setHasTwitter(boolean hasTwitter) {
        this.hasTwitter = hasTwitter;
    }

    @Override
    public void setNbTwitter(int nbTwitter) {
        this.nbTwitter = nbTwitter;
    }

    @Override
    public void setHasGooglePlus(boolean hasGooglePlus) {
        this.hasGooglePlus = hasGooglePlus;
    }

    @Override
    public void setNbGooglePlus(int nbGooglePlus) {
        this.nbGooglePlus = nbGooglePlus;
    }

    @Override
    public void setHasPinterest(boolean hasPinterest) {
        this.hasPinterest = hasPinterest;
    }

    @Override
    public void setNbPinterest(int nbPinterest) {
        this.nbPinterest = nbPinterest;
    }
}
