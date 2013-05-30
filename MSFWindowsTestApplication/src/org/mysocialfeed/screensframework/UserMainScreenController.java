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
    private Label welcomeMessage = new Label();
    @FXML
    private Label userHasAccount = new Label();
    
    // List of action buttons :    
    @FXML
    private Button accessFacebook = new Button();
    @FXML
    private Button accessTwitter = new Button();
    @FXML
    private Button accessGooglePlus = new Button();
    @FXML
    private Button accessPinterest = new Button();
    @FXML
    private Button addAccount = new Button();
    @FXML
    private Button addFirstAccount = new Button();
    
    // No Social Account error messages :
    @FXML
    private Label noAccountAvailable = new Label();
    @FXML
    private Label noFacebookAccount = new Label();
    @FXML
    private Label noTwitterAccount = new Label();
    @FXML
    private Label noGooglePlusAccount = new Label();
    @FXML
    private Label noPinterestAccount = new Label();
     
    
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
            this.welcomeMessage.setText("Welcome " + userDataService.getUserName() + "!");
                if (userDataService.hasAccount() == false) {
                    this.noAccountAvailable.setVisible(true);
                    this.addFirstAccount.setVisible(true);
                    this.accessFacebook.setVisible(false);
                    this.accessGooglePlus.setVisible(false);
                    this.accessPinterest.setVisible(false);
                    this.accessTwitter.setVisible(false);
                } 
                else if (userDataService.hasAccount() == true) {
                    this.noAccountAvailable.setVisible(false);
                    this.addFirstAccount.setVisible(false);
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
           // this.welcomeMessage.setText("Something went wrong because you were not properly authenticated.\nPlease, sign off and sign in again.");
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
