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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.services.interfaces.SocialAccountService;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class AddFacebookAccountScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    private final SocialAccountService socialAccountService;
    
    @Inject
    public AddFacebookAccountScreenController(SocialAccountService socialAccountService){
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
            this.socialAccountService.insertNewAccountIntoDatabase(
                    userFirstName.getText(), userLastName.getText(), this.userEmailAddr.getText(), "Fb");
        }
    }
}
