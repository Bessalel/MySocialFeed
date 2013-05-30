/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.mysocialfeed.services.interfaces.UserService;
import org.mysocialfeed.services.repository.UserDataService;

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserMainScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    @FXML
    private Label welcomeMessage;
    @FXML
    private Label userHasAccount;
    
    // List of action buttons :    
    @FXML
    private Button accessFacebook;
    @FXML
    private Button accessTwitter;
    @FXML
    private Button accessGooglePlus;
    @FXML
    private Button accessPinterest;
    @FXML
    private Button addAccount;
    @FXML
    private Button addFirstAccount;
    
    // No Social Account error messages :
    @FXML
    private Label noAccountAvailable;
    @FXML
    private Label noFacebookAccount;
    @FXML
    private Label noTwitterAccount;
    @FXML
    private Label noGooglePlusAccount;
    @FXML
    private Label noPinterestAccount;
     
    
    private final UserService userService;
    private final UserDataService userDataService;
    
    @Inject
    public UserMainScreenController(UserService userService, UserDataService userDataService){
        this.userService = userService;
        this.userDataService = userDataService;
        setDefaultProperties();
    }
    
    private void setDefaultProperties(){
        if (userService.isAuthenticated() == true) {
                if (userDataService.hasAccount() == false) {
                    this.noAccountAvailable.setVisible(true);
                    this.accessFacebook.setVisible(false);
                    this.accessGooglePlus.setVisible(false);
                    this.accessPinterest.setVisible(false);
                    this.accessTwitter.setVisible(false);
                } 
                else if (userDataService.hasAccount() == true) {
                    if (userDataService.hasFacebook() == true) {
                        this.accessFacebook.setVisible(true);
                    }
                    if (userDataService.hasGooglePlus() == true) {
                        this.accessGooglePlus.setVisible(true);
                    }
                    if (userDataService.hasPinterest() == true) {
                        this.accessPinterest.setVisible(true);
                    }
                    if (userDataService.hasTwitter() == true) {
                        this.accessTwitter.setVisible(true);
                }
            }
        } else if (userService.isAuthenticated() == false) {
            this.welcomeMessage.setText("Something went wrong because you were not properly authenticated.\nPlease, sign off and sign in again.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
        
    }
    
        
    @FXML
    private void userChooseFacebook(ActionEvent event) {
        if (userDataService.hasFacebook() == false) {
            this.noFacebookAccount.setVisible(true);
        } else {
            myController.setScreen(FXMLGetResourcer.userFacebookScreenID);
        }
    }
    
    @FXML
    private void userChooseTwitter(ActionEvent event) {
        if (userDataService.hasTwitter() == false) {
            this.noTwitterAccount.setVisible(true);
        } else {
            myController.setScreen(FXMLGetResourcer.userTwitterScreenID);
        }
    }
    
    @FXML
    private void userChooseGooglePlus(ActionEvent event) {
        if (userDataService.hasGooglePlus() == false) {
            this.noGooglePlusAccount.setVisible(true);
        } else {
            //
        }
    }
    
    @FXML
    private void userChoosePinterest(ActionEvent event) {
        if (userDataService.hasPinterest() == false) {
            this.noPinterestAccount.setVisible(true);
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
        
        userService.userSignOff();
        
        this.noFacebookAccount.setVisible(false);
        this.noTwitterAccount.setVisible(false);
        this.noGooglePlusAccount.setVisible(false);
        this.noPinterestAccount.setVisible(false);
        
        this.welcomeMessage.setText(this.welcomeMessage.getText().substring(0, 7));
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
