/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs.addsocialaccounts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class AccountSuccessfullyAddedScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public static Label successMessage;
    
    @FXML
    private void addAnotherFacebookAccount(ActionEvent event) {
        AccountSuccessfullyAddedScreenController.successMessage.setText(
                AccountSuccessfullyAddedScreenController.successMessage.getText().substring(0, 1));
        myController.setScreen(FXMLGetResourcer.addFacebookAccountScreenID);
    }
    
    @FXML
    private void addAnotherSocialAccount(ActionEvent event) {
        AccountSuccessfullyAddedScreenController.successMessage.setText(
                AccountSuccessfullyAddedScreenController.successMessage.getText().substring(0, 1));
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
    }
    
    @FXML
    private void goBackToUserMainScreen(ActionEvent event) {
        AccountSuccessfullyAddedScreenController.successMessage.setText(
                AccountSuccessfullyAddedScreenController.successMessage.getText().substring(0, 1));
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
}
