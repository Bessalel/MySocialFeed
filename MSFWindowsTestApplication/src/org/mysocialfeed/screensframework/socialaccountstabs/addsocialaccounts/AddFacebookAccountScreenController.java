/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs.addsocialaccounts;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.services.interfaces.SocialAccountService;
import org.mysocialfeed.services.repository.UserDataService;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class AddFacebookAccountScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    private final UserDataService userDataService;
    private final SocialAccountService socialAccountService;
    
    @Inject
    public AddFacebookAccountScreenController(UserDataService userDataService, SocialAccountService socialAccountService){
        this.userDataService = userDataService;
        this.socialAccountService = socialAccountService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    // Text fields :
    @FXML final private TextField userFirstName = new TextField();
    @FXML final private TextField userLastName = new TextField();
    @FXML final private TextField userEmailAddr = new TextField();
    
    // Error messages :
    @FXML final private Label noFirstName = new Label(); 
    @FXML final private Label noLastName = new Label();;
    @FXML final private Label noEmailAddr = new Label();;
    
    
    // Success messages :
    @FXML final private Label successMessage1 = new Label();
    @FXML final private Label successMessage2 = new Label();
    @FXML final private Button addAnotherFbAccount = new Button();
    @FXML final private Button goToUserMainScreen = new Button();
    
    // Failure messages :
    @FXML final private Label failureMessage1 = new Label();
    @FXML final private Label failureMessage2 = new Label();
    @FXML final private Button tryAgain = new Button();
    
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void userCancelled(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
    }
    
    @FXML
    private void addUserFacebookAccount(ActionEvent event) {
        if (this.userFirstName.getText().isEmpty()) {
            this.noFirstName.setVisible(true);
        } else if (!(this.userFirstName.getText().isEmpty())) {
            this.noFirstName.setVisible(false);
        }
        if (this.userLastName.getText().isEmpty()) {
            this.noLastName.setVisible(true);
        } else if (!(this.userLastName.getText().isEmpty())) {
            this.noLastName.setVisible(false);
        }
        if (this.userEmailAddr.getText().isEmpty()) {
            this.noEmailAddr.setVisible(true);
        } else if (!(this.userEmailAddr.getText().isEmpty())) {
            this.noEmailAddr.setVisible(false);
        }
        
        if (!(this.userFirstName.getText().isEmpty())
              && !(this.userLastName.getText().isEmpty())
              && !(this.userEmailAddr.getText().isEmpty())) {
            if (this.socialAccountService.insertNewAccountIntoDatabase(
                    userFirstName.getText(), userLastName.getText(), this.userEmailAddr.getText(), "Fb") ) {
                this.userDataService.setNbFacebook(this.userDataService.getNbFbAccount() + 1);
                this.successMessage1.setVisible(true);
                this.successMessage2.setVisible(true);
                this.addAnotherFbAccount.setVisible(true);
                this.goToUserMainScreen.setVisible(true);
                
                this.failureMessage1.setVisible(false);
                this.failureMessage1.setVisible(false);
                this.tryAgain.setVisible(false);
                
            } else {
                this.successMessage1.setVisible(false);
                this.successMessage2.setVisible(false);
                this.addAnotherFbAccount.setVisible(false);
                this.goToUserMainScreen.setVisible(false);
                
                this.failureMessage1.setVisible(true);
                this.failureMessage1.setVisible(true);
                this.tryAgain.setVisible(true);
            }
        }
    }
    
    @FXML
    private void addAnotherFbAccount(ActionEvent e) {
        this.myController.setScreen(FXMLGetResourcer.addFacebookAccountScreenID);
    }
    
    @FXML
    private void goToMainScreen(ActionEvent e) {
        this.myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void userTryAgain(ActionEvent e) {
        this.userFirstName.setText(null);
        this.userLastName.setText(null);
        this.userEmailAddr.setText(null);
    }
}
