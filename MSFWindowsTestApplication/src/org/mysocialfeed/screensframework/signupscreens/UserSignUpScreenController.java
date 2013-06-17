/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.signupscreens;

import com.google.inject.Inject;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.mysocialfeed.models.DatabaseManager;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.screensframework.WelcomeScreenController;
import org.mysocialfeed.services.interfaces.UserService;
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserSignUpScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    // Error Messages labels
    @FXML private Label usernameEmpty = new Label();
    @FXML private Label firstNameEmpty = new Label();
    @FXML private Label lastNameEmpty = new Label();
    @FXML private Label emailAddrEmpty = new Label();
    @FXML private Label passwordEmpty = new Label();
    @FXML private Label confirmPwdEmpty = new Label();
    @FXML private Label pwdDifferent = new Label();
      
    
    // Field variables
    @FXML private TextField userNameTextField = new TextField();
    @FXML private TextField firstNameTextField = new TextField();
    @FXML private TextField lastNameTextField = new TextField();
    @FXML private TextField emailAddrTextField = new TextField();
    @FXML private PasswordField passwordField = new PasswordField();
    @FXML private PasswordField confirmPasswordField = new PasswordField();
    
    
    @FXML AnchorPane mainAnchorPane;
    
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
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public void createUserAccount(ActionEvent event) {
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
               !(userNameTextField.getText().isEmpty())
            && !(firstNameTextField.getText().isEmpty())
            && !(lastNameTextField.getText().isEmpty())
            && !(emailAddrTextField.getText().isEmpty())
            && !(passwordField.getText().isEmpty())
            && !(confirmPasswordField.getText().isEmpty())
            && !(passwordField.getText().compareTo(confirmPasswordField.getText()) != 0)) {
            insertUserIntoDatabase();
        }
    }

    @FXML
    public void returnUserBackToWelcomeScreen(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
    
    private void insertUserIntoDatabase() {
        
    }
}
