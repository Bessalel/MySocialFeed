/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.signupscreens;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.services.interfaces.UserService;



/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserSignUpScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    // Error Messages labels
    @FXML private final Label usernameEmpty = new Label();
    @FXML private final Label firstNameEmpty = new Label();
    @FXML private final Label lastNameEmpty = new Label();
    @FXML private final Label emailAddrEmpty = new Label();
    @FXML private final Label passwordEmpty = new Label();
    @FXML private final Label confirmPwdEmpty = new Label();
    @FXML private final Label pwdDifferent = new Label();
    @FXML private final Label accountCreationFailed1 = new Label();
    @FXML private final Label accountCreationFailed2 = new Label();
    
    // Success Messages
    @FXML private final Label successMessage1 = new Label();
    @FXML private final Label successMessage2 = new Label();
    
    // Field variables
    @FXML private TextField userNameTextField = new TextField();
    @FXML private TextField firstNameTextField = new TextField();
    @FXML private TextField lastNameTextField = new TextField();
    @FXML private TextField emailAddrTextField = new TextField();
    @FXML private PasswordField passwordField = new PasswordField();
    @FXML private PasswordField confirmPasswordField = new PasswordField();
    
    @FXML private final Button logNewUserIn = new Button();
    @FXML private final Button endProgram = new Button();
    @FXML private final Button startAllOver = new Button();
    
    private final UserService userService;
    
    @Inject
    public UserSignUpScreenController(UserService userService){
        this.userService = userService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDefaultProperties();
    }    

    private void setDefaultProperties() {
        usernameEmpty.setVisible(false);
        firstNameEmpty.setVisible(false);
        lastNameEmpty.setVisible(false);
        emailAddrEmpty.setVisible(false);
        passwordEmpty.setVisible(false);
        confirmPwdEmpty.setVisible(false);  
        pwdDifferent.setVisible(false);
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    public void createUserAccount(ActionEvent event) {
        // Just checking if user did fill out all fields
        if (userNameTextField.getText().isEmpty()) {
            usernameEmpty.setVisible(true);
        } 
        if (firstNameTextField.getText().isEmpty()) {
            firstNameEmpty.setVisible(true);
        } 
        if (lastNameTextField.getText().isEmpty()) {
            lastNameEmpty.setVisible(true);
        } 
        if(emailAddrTextField.getText().isEmpty()) {
            emailAddrEmpty.setVisible(true);
        } 
        if (passwordField.getText().isEmpty()) { 
            passwordEmpty.setVisible(true);
        } 
        if (confirmPasswordField.getText().isEmpty()) {
            confirmPwdEmpty.setVisible(true);
        } 
        if (passwordField.getText().compareTo(confirmPasswordField.getText()) != 0){
            pwdDifferent.setVisible(true);
        } else if (
            // Just making sure all fields were filled out by user
               !(userNameTextField.getText().isEmpty())
            && !(firstNameTextField.getText().isEmpty())
            && !(lastNameTextField.getText().isEmpty())
            && !(emailAddrTextField.getText().isEmpty())
            && !(passwordField.getText().isEmpty())
            && !(confirmPasswordField.getText().isEmpty())
            && !(passwordField.getText().compareTo(confirmPasswordField.getText()) != 0)) {
            
            if (this.userService.insertNewUserIntoDatabase(userNameTextField.getText(), 
                    passwordField.getText(), firstNameTextField.getText(), 
                    lastNameTextField.getText(), emailAddrTextField.getText()) == true) {
                this.successMessage1.setVisible(true);
                this.successMessage2.setVisible(true);
                this.logNewUserIn.setVisible(true);
            } else {
                this.accountCreationFailed1.setVisible(true);
                this.accountCreationFailed2.setVisible(true);
                this.endProgram.setVisible(true);
                this.startAllOver.setVisible(true);
            }
        }
    }

    @FXML
    public void returnUserBackToWelcomeScreen(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }

    @FXML
    private void startAccountCreationOver(ActionEvent e) {
        this.userNameTextField.setText(null);
        this.firstNameTextField.setText(null);
        this.lastNameTextField.setText(null);
        this.emailAddrTextField.setText(null);
        this.passwordField.setText(null);
        this.confirmPasswordField.setText(null);
    }
    
    @FXML
    private void endProgram(ActionEvent e) {
        System.exit(0);
    }
    
    @FXML
    private void logNewUserIn(ActionEvent e) {
        this.userService.authenticate(this.userNameTextField.getText(), this.passwordField.getText());
        this.myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
}
