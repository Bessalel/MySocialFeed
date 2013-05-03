/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.models;

// This class will contain all user data info in order to avoid multiple unecessary
// access to MySQL server

/**
 *
 * @author Vincent
 */
public class UserData {
    private boolean connectionStatus;
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

    public UserData(boolean connectionStatus, int userID, String userName, 
            String userFirstName, String userLastName, String userEmailAddress, 
            boolean hasFacebook, int nbFacebook, boolean hasTwitter, int nbTwitter, 
            boolean hasGooglePlus, int nbGooglePlus, boolean hasPinterest, int nbPinterest) {
        this.connectionStatus = connectionStatus;
        this.userID = userID;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailAddress = userEmailAddress;
        this.hasFacebook = hasFacebook;
        this.nbFacebook = nbFacebook;
        this.hasTwitter = hasTwitter;
        this.nbTwitter = nbTwitter;
        this.hasGooglePlus = hasGooglePlus;
        this.nbGooglePlus = nbGooglePlus;
        this.hasPinterest = hasPinterest;
        this.nbPinterest = nbPinterest;
    }

    public boolean isConnected() {
        return connectionStatus;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public boolean hasFacebook() {
        return hasFacebook;
    }

    public int getNbFacebook() {
        return nbFacebook;
    }

    public boolean hasTwitter() {
        return hasTwitter;
    }

    public int getNbTwitter() {
        return nbTwitter;
    }

    public boolean hasGooglePlus() {
        return hasGooglePlus;
    }

    public int getNbGooglePlus() {
        return nbGooglePlus;
    }

    public boolean hasPinterest() {
        return hasPinterest;
    }

    public int getNbPinterest() {
        return nbPinterest;
    }
    
    
}
