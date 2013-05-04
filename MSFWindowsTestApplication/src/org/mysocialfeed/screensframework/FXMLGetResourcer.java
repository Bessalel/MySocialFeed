/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import java.net.URL;

/**
 *
 * @author Windows
 */
public class FXMLGetResourcer {
    
    private final static String SIGNUPS_SCREENS_LOCATOR = 
            "/org/mysocialfeed/screensframework/signupscreens/";
    private final static String SOCIAL_ACCOUNTS_SCREENS_LOCATOR = 
            "/org/mysocialfeed/screensframework/socialaccountstabs/";
    
    public final static String welcomeScreenID = "WelcomeScreen";
    private URL welcomeScreenFile;
    
    public final static String userMainScreenID = "UserMainScree";
    private URL userMainScreenFile;
    
    public final static String userSignUpScreenID = "UserSignUpScreen";
    private URL userSignUpScreenFile;
    
    public final static String userAccountCreatedScreenID = "UserAccountCreatedScreenID";
    private URL userAccountCreatedScreenFile;

    public final static String userFacebookScreenID = "UserFacebookScreen";
    private URL userFacebookScreenFile;
    
    public final static String userGooglePlusScreenID = "UserGooglePlusScreen";
    private URL userGooglePlusScreenFile;
    
    public final static String userPinterestScreenID = "UserPinterestScreen";
    private URL userPinterestScreenFile;
    
    public final static String userTwitterScreenID = "UserTwitterScreen";
    private URL userTwitterScreenFile;
    
    public FXMLGetResourcer() {
        welcomeScreenFile = FXMLGetResourcer.class.getResource("WelcomeScreen.fxml");
        userMainScreenFile = FXMLGetResourcer.class.getResource("UserMainScreen.fxml");
        
        userSignUpScreenFile = FXMLGetResourcer.class.getResource(SIGNUPS_SCREENS_LOCATOR + "UserSignUpScreen.fxml");
        userAccountCreatedScreenFile = FXMLGetResourcer.class.getResource(SIGNUPS_SCREENS_LOCATOR + "UserAccountCreatedScreen.fxml");
        
        userFacebookScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserFacebookScreen.fxml");
        userGooglePlusScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserGooglePlusScreen.fxml");
        userPinterestScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserPinterestScreen.fxml");
        userTwitterScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserTwitterScreen.fxml");
    }

    public URL getWelcomeScreenFile() {
        return welcomeScreenFile;
    }

    public URL getUserMainScreenFile() {
        return userMainScreenFile;
    }

    public URL getUserSignUpScreenFile() {
        return userSignUpScreenFile;
    }

    public URL getUserAccountCreatedScreenFile() {
        return userAccountCreatedScreenFile;
    }

    public URL getUserFacebookScreenFile() {
        return userFacebookScreenFile;
    }

    public URL getUserGooglePlusScreenFile() {
        return userGooglePlusScreenFile;
    }

    public URL getUserPinterestScreenFile() {
        return userPinterestScreenFile;
    }

    public URL getUserTwitterScreenFile() {
        return userTwitterScreenFile;
    }   
 }
