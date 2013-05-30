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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.screensframework.UserMainScreenController;
import org.mysocialfeed.screensframework.socialaccountstabs.UserAddAccountScreenController;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class AccountCreationResultScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @Inject
    public AccountCreationResultScreenController(){
        System.out.println("ACRSC is OK");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    

    @FXML
    public static Label successMessage1;
    @FXML
    public static Label successMessage2;
    
    @FXML
    public static Label failureMessage;
    
    
    @FXML
    public static Button retry;
    @FXML
    public static Button cancel;
    
    
    @FXML
    public static Button addAnotherAccount;
    @FXML
    public static Button addAnotherFbAccount;
    @FXML
    public static Button mainPage;
    
    
    @FXML
    private void addAnotherFacebookAccount(ActionEvent event) {
        AccountCreationResultScreenController.failureMessage.setVisible(false);
        AccountCreationResultScreenController.successMessage1.setVisible(false);
        AccountCreationResultScreenController.successMessage2.setVisible(false);
        
        AccountCreationResultScreenController.successMessage2.setText(
            AccountCreationResultScreenController.successMessage2.getText().substring(0, 1));
        
        AccountCreationResultScreenController.retry.setVisible(false);
        AccountCreationResultScreenController.cancel.setVisible(false);
        AccountCreationResultScreenController.addAnotherAccount.setVisible(false);
        AccountCreationResultScreenController.addAnotherFbAccount.setVisible(false);
        AccountCreationResultScreenController.mainPage.setVisible(false);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(615);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.addFacebookAccountScreenID);
    }
    
    @FXML
    private void addAnotherSocialAccount(ActionEvent event) {
        AccountCreationResultScreenController.failureMessage.setVisible(false);
        AccountCreationResultScreenController.successMessage1.setVisible(false);
        AccountCreationResultScreenController.successMessage2.setVisible(false);
        
        AccountCreationResultScreenController.successMessage2.setText(
            AccountCreationResultScreenController.successMessage2.getText().substring(0, 1));
        
        AccountCreationResultScreenController.retry.setVisible(false);
        AccountCreationResultScreenController.cancel.setVisible(false);
        AccountCreationResultScreenController.addAnotherAccount.setVisible(false);
        AccountCreationResultScreenController.addAnotherFbAccount.setVisible(false);
        AccountCreationResultScreenController.mainPage.setVisible(false);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(605);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
    }
    
    @FXML
    private void goBackToUserMainScreen(ActionEvent event) {
        AccountCreationResultScreenController.failureMessage.setVisible(false);
        AccountCreationResultScreenController.successMessage1.setVisible(false);
        AccountCreationResultScreenController.successMessage2.setVisible(false);
        
        AccountCreationResultScreenController.successMessage2.setText(
            AccountCreationResultScreenController.successMessage2.getText().substring(0, 1));
        
        AccountCreationResultScreenController.retry.setVisible(false);
        AccountCreationResultScreenController.cancel.setVisible(false);
        AccountCreationResultScreenController.addAnotherAccount.setVisible(false);
        AccountCreationResultScreenController.addAnotherFbAccount.setVisible(false);
        AccountCreationResultScreenController.mainPage.setVisible(false);
        
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
}
