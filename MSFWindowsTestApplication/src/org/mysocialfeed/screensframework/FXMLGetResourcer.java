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
    
    // Shortcuts to differents packages where screens are stored and organized :
    private final static String SIGNUPS_SCREENS_LOCATOR = 
            "/org/mysocialfeed/screensframework/signupscreens/";
    private final static String SOCIAL_ACCOUNTS_SCREENS_LOCATOR = 
            "/org/mysocialfeed/screensframework/socialaccountstabs/";
    private final static String ADD_SOCIAL_ACCOUNTS_SCREENS_LOCATOR =
            "/org/mysocialfeed/screensframework/socialaccountstabs/addsocialaccounts/";
    
    // Main screens :
    public final static String welcomeScreenID = "WelcomeScreen";
    private URL welcomeScreenFile;
    public final static String userMainScreenID = "UserMainScreen";
    private URL userMainScreenFile;
    public final static String userSignUpScreenID = "UserSignUpScreen";
    private URL userSignUpScreenFile;
    public final static String userAccountCreatedScreenID = "UserAccountCreatedScreenID";
    private URL userAccountCreatedScreenFile;

    // Screens for social accounts display :
    public final static String userFacebookScreenID = "UserFacebookScreen";
    private URL userFacebookScreenFile;
    public final static String userGooglePlusScreenID = "UserGooglePlusScreen";
    private URL userGooglePlusScreenFile;
    public final static String userPinterestScreenID = "UserPinterestScreen";
    private URL userPinterestScreenFile;
    public final static String userTwitterScreenID = "UserTwitterScreen";
    private URL userTwitterScreenFile;
    public final static String userAddAccountScreenID = "UserAddAccountScreen";
    private URL userAddAccountScreenFile;
    
    // Screens for adding social network accounts :
    public final static String addFacebookAccountScreenID = "AddFacebookAccountScreen";
    private URL addFacebookAccountScreenFile;
    public final static String addGooglePlusAccountScreenID = "AddGooglePluskAccountScreen";
    private URL addGooglePlusAccountScreenFile;
    public final static String addPinterestAccountScreenID = "AddPinterestAccountScreen";
    private URL addPinterestAccountScreenFile;
    public final static String addTwitterAccountScreenID = "AddTwitterAccountScreen";
    private URL addTwitterAccountScreenFile;
    public final static String AccountCreationResultScreenID = "AddSuccessfullyAddedScreen";
    private URL accountCreationResultScreenFile;
    
    
    public FXMLGetResourcer() {
        welcomeScreenFile = FXMLGetResourcer.class.getResource("WelcomeScreen.fxml");
        userMainScreenFile = FXMLGetResourcer.class.getResource("UserMainScreen.fxml");
        
        userSignUpScreenFile = FXMLGetResourcer.class.getResource(SIGNUPS_SCREENS_LOCATOR + "UserSignUpScreen.fxml");
        userAccountCreatedScreenFile = FXMLGetResourcer.class.getResource(SIGNUPS_SCREENS_LOCATOR + "UserAccountCreatedScreen.fxml");
        
        userFacebookScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserFacebookScreen.fxml");
        userGooglePlusScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserGooglePlusScreen.fxml");
        userPinterestScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserPinterestScreen.fxml");
        userTwitterScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserTwitterScreen.fxml");
        userAddAccountScreenFile = FXMLGetResourcer.class.getResource(SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "UserAddAccountScreen.fxml");
        
        addFacebookAccountScreenFile = FXMLGetResourcer.class.getResource(ADD_SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "AddFacebookAccountScreen.fxml");
        addGooglePlusAccountScreenFile = FXMLGetResourcer.class.getResource(ADD_SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "AddGooglePlusAccountScreen.fxml");
        addPinterestAccountScreenFile = FXMLGetResourcer.class.getResource(ADD_SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "AddPinterestAccountScreen.fxml");
        addTwitterAccountScreenFile = FXMLGetResourcer.class.getResource(ADD_SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "AddTwitterAccountScreen.fxml");
        accountCreationResultScreenFile = FXMLGetResourcer.class.getResource(ADD_SOCIAL_ACCOUNTS_SCREENS_LOCATOR + "AccountCreationResultScreen.fxml");
        
    }

    public URL getWelcomeScreenFile() {
        return welcomeScreenFile;
    }

    public URL getUserMainScreenFile() {
        return userMainScreenFile;
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

    public URL getUserAddAccountScreenFile() {
        return userAddAccountScreenFile;
    }

    public URL getAddFacebookScreenAccountScreenFile() {
        return addFacebookAccountScreenFile;
    }

    public URL getAddGooglePlusAccountScreenFile() {
        return addGooglePlusAccountScreenFile;
    }

    public URL getAddPinterestAccountScreenFile() {
        return addPinterestAccountScreenFile;
    }

    public URL getAddTwitterAccountScreenFile() {
        return addTwitterAccountScreenFile;
    }

    public URL getAccountCreationResultScreenFile() {
        return accountCreationResultScreenFile;
    }

    public URL getUserSignUpScreenFile() {
        return userSignUpScreenFile;
    }
 }
