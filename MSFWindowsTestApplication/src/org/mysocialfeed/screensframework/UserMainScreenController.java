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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mysocialfeed.services.interfaces.UserService;
import org.mysocialfeed.services.repository.UserDataService;

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserMainScreenController extends ControlledScreen implements Initializable {
    
    @FXML private final Text welcomeMessage = new Text();
    @FXML private final Label userHasAccount = new Label();
    
    // List of action buttons and labels :    
    @FXML private final Button accessFacebook = new Button();
    @FXML private final Label describeFb = new Label();
    
    @FXML private final Button accessTwitter = new Button();
    @FXML private final Label describeTw = new Label();
    
    @FXML private final Button accessGooglePlus = new Button();
    @FXML private final Label describeGplus = new Label();
    
    @FXML private final Button accessPinterest = new Button();
    @FXML private final Label describePin = new Label();
    
    @FXML private final Button addAccount = new Button();
    @FXML private final Label describeAddAccount = new Label();
    
    @FXML private final Button addFirstAccount = new Button();
    
    // No Social Account error messages :
    @FXML private final Label noAccountAvailable = new Label();
    @FXML private final Label noFacebookAccount = new Label();
    @FXML private final Label noTwitterAccount = new Label();
    @FXML private final Label noGooglePlusAccount = new Label();
    @FXML private final Label noPinterestAccount = new Label();
     
    @FXML private final ImageView mainBanner = new ImageView();
    
    private final UserService userService;
    private final UserDataService userDataService;
    
    @Inject
    public UserMainScreenController(UserService userService, UserDataService userDataService){
        this.userService = userService;
        this.userDataService = userDataService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDefaultProperties();
    }
    
    @Override
    protected void onActivated() {
        setDefaultProperties();
    }
    
    private void resetErrorMessages() {
        this.noAccountAvailable.setVisible(false);
        this.noFacebookAccount.setVisible(false);
        this.noTwitterAccount.setVisible(false);
        this.noGooglePlusAccount.setVisible(false);
        this.noPinterestAccount.setVisible(false);
    }
    
    private void setDefaultProperties(){
        if (userService.isAuthenticated() == true) {
            this.welcomeMessage.setText("Welcome " + userDataService.getUserName() + "!");
                if (userDataService.hasAccount() == false) {
                    this.userHasAccount.setVisible(false);
                    
                    this.noAccountAvailable.setVisible(true);
                    this.addFirstAccount.setVisible(true);
                    
                    this.addAccount.setVisible(false);
                    this.describeAddAccount.setVisible(false);
                    
                    this.accessFacebook.setVisible(false);
                    this.describeFb.setVisible(false);
                    
                    this.accessGooglePlus.setVisible(false);
                    this.describeGplus.setVisible(false);
                    
                    this.accessPinterest.setVisible(false);
                    this.describePin.setVisible(false);
                    
                    this.accessTwitter.setVisible(false);
                    this.describeTw.setVisible(false);
                } 
                else if (userDataService.hasAccount() == true) {
                    this.userHasAccount.setVisible(true);
                    this.noAccountAvailable.setVisible(false);
                    this.addFirstAccount.setVisible(false);
                    if (userDataService.hasFacebook() == true) {
                        this.accessFacebook.setVisible(true);
                        this.describeFb.setVisible(true);
                    }
                    if (userDataService.hasGooglePlus() == true) {
                        this.accessGooglePlus.setVisible(true);
                        this.describeGplus.setVisible(true);
                    }
                    if (userDataService.hasPinterest() == true) {
                        this.accessPinterest.setVisible(true);
                        this.describePin.setVisible(true);
                    }
                    if (userDataService.hasTwitter() == true) {
                        this.accessTwitter.setVisible(true);
                        this.describeTw.setVisible(true);
                }
            }
        } else if (userService.isAuthenticated() == false) {
           this.welcomeMessage.setText("Something went wrong because you were not properly authenticated.\nPlease, sign off and sign in again.");
        }
    }
    
    @FXML
    private void userChooseFacebook(ActionEvent event) {
        if (userDataService.hasFacebook() == false) {
            this.noFacebookAccount.setVisible(true);
        } else {
            resetErrorMessages();
            this.getScreenController().setScreen(FXMLGetResourcer.userFacebookScreenID);
        }
    }
    
    @FXML
    private void userChooseTwitter(ActionEvent event) {
        if (userDataService.hasTwitter() == false) {
            this.noTwitterAccount.setVisible(true);
        } else {
            resetErrorMessages();
            this.getScreenController().setScreen(FXMLGetResourcer.userTwitterScreenID);
        }
    }
    
    @FXML
    private void userChooseGooglePlus(ActionEvent event) {
        if (userDataService.hasGooglePlus() == false) {
            this.noGooglePlusAccount.setVisible(true);
        } else {
            resetErrorMessages();
            //
        }
    }
    
    @FXML
    private void userChoosePinterest(ActionEvent event) {
        if (userDataService.hasPinterest() == false) {
            this.noPinterestAccount.setVisible(true);
        } else {
            resetErrorMessages();
            //
        }
    }
    
    @FXML
    private void addUserAccount(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(605);
        stage.setHeight(430);
        this.getScreenController().setScreen(FXMLGetResourcer.userAddAccountScreenID);
    }
    
    @FXML
    private void logUserOff(ActionEvent event) {
        userService.userSignOff();
        resetErrorMessages();
        this.getScreenController().setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
