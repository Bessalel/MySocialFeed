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
    Label noFacebookAccount;
    
    @FXML
    Label noTwitterAccount;
    
    @FXML
    Label noGooglePlusAccount;
    
    @FXML
    Label noPinterestAccount;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
        
    @FXML
    private void userChooseFacebook(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
           if (!(Context.getCurrentUser().hasFacebook())) {
               noFacebookAccount.setVisible(false);
           }
        }
        // myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
    }
    
    @FXML
    private void userChooseTwitter(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
          if (!(Context.getCurrentUser().hasTwitter())) {
               noTwitterAccount.setVisible(false);
           }  
        }
        myController.setScreen(FXMLGetResourcer.userTwitterScreenID);
    }
    
    @FXML
    private void userChooseGooglePlus(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
            if (!(Context.getCurrentUser().hasGooglePlus())) {
               noTwitterAccount.setVisible(false);
           }
        }
      //  myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
    }
    
    @FXML
    private void userChoosePinterest(ActionEvent event) {
        if (Context.getCurrentUser() != null) {
            if (!(Context.getCurrentUser().hasPinterest())) {
               noPinterestAccount.setVisible(false);
           }
        }
      //  myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
    }
    
    @FXML
    private void addUserAccount(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(600);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
    }
    
    @FXML
    private void logUserOff(ActionEvent event) {
        UserMainScreenController.welcomeMessage.setText(UserMainScreenController.welcomeMessage.getText().substring(0, 7));
        WelcomeScreenController.errorMessage.setVisible(false);
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
