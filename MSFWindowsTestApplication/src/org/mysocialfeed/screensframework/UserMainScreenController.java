/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.mysocialfeed.models.Context;
import org.mysocialfeed.screensframework.socialaccountstabs.UserFacebookScreenController;

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserMainScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    @FXML
    public static Label welcomeMessage;
    @FXML
    public static Label userHasAccount;
    
    // List of action buttons :
    
    @FXML
    public static Button accessFacebook;
    @FXML
    public static Button accessTwitter;
    @FXML
    public static Button accessGooglePlus;
    @FXML
    public static Button accessPinterest;
    @FXML
    public static Button addAccount;
    @FXML
    public static Button addFirstAccount;
    
    // No Social Account error messages :
    
    @FXML
    public static Label noAccountAvailable;
    
    @FXML
    private Label noFacebookAccount;
    @FXML
    private Label noTwitterAccount;
    @FXML
    private Label noGooglePlusAccount;
    @FXML
    private Label noPinterestAccount;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
        
    }
    
        
    @FXML
    private void userChooseFacebook(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
           if (!(Context.getCurrentUser().hasFacebook())) {
               noFacebookAccount.setVisible(true);
           }else {
               UserFacebookScreenController.loadUserFbTimeline();
               myController.setScreen(FXMLGetResourcer.userFacebookScreenID);
            }
        } 
    }
    
    @FXML
    private void userChooseTwitter(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
          if (!(Context.getCurrentUser().hasTwitter())) {
               noTwitterAccount.setVisible(true);
           } else
              myController.setScreen(FXMLGetResourcer.userTwitterScreenID);
        }
    }
    
    @FXML
    private void userChooseGooglePlus(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
            if (!(Context.getCurrentUser().hasGooglePlus())) {
               noGooglePlusAccount.setVisible(true);
           }
        }
    }
    
    @FXML
    private void userChoosePinterest(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
            if (!(Context.getCurrentUser().hasPinterest())) {
               noPinterestAccount.setVisible(true);
           }
        }
    }
    
    @FXML
    private void addUserAccount(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(605);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
    }
    
    @FXML
    private void logUserOff(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
        
        noFacebookAccount.setVisible(false);
        noTwitterAccount.setVisible(false);
        noGooglePlusAccount.setVisible(false);
        noPinterestAccount.setVisible(false);
        
        UserMainScreenController.welcomeMessage.setText(UserMainScreenController.welcomeMessage.getText().substring(0, 7));
        WelcomeScreenController.errorMessage.setVisible(false);
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
