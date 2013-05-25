/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.signupscreens;

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
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserSignUpScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    // Error Messages labels
    
    @FXML
    private Label usernameEmpty;
    @FXML
    private Label firstNameEmpty;
    @FXML
    private Label lastNameEmpty;
    @FXML
    private Label emailAddrEmpty;
    @FXML
    private Label passwordEmpty;
    @FXML
    private Label confirmPwdEmpty;
    @FXML
    private Label pwdDifferent;
      
    
    // Field variables
    
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailAddrTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    
    
    @FXML
    AnchorPane mainAnchorPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public void createUserAccount(ActionEvent event) {
            usernameEmpty.setVisible(false);
            firstNameEmpty.setVisible(false);
            lastNameEmpty.setVisible(false);
            emailAddrEmpty.setVisible(false);
            passwordEmpty.setVisible(false);
            confirmPwdEmpty.setVisible(false);  
            pwdDifferent.setVisible(false);

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
            } else
                return ;
    }

    @FXML
    public void returnUserBackToWelcomeScreen(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
        WelcomeScreenController.errorMessage.setVisible(false);
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
    
    private void insertUserIntoDatabase() {
        try {
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                MSFWindowsTestApplication.accessAndSetupSQLServer(false);
                }
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                    try (PreparedStatement insertNewUser = 
                            MSFWindowsTestApplication.conn.prepareStatement(
                            DatabaseManager.INSERT_USER)) {
                                insertNewUser.setString(1, userNameTextField.getText());
                                insertNewUser.setString(2, passwordField.getText());
                                insertNewUser.setString(3, firstNameTextField.getText());
                                insertNewUser.setString(4, lastNameTextField.getText());
                                insertNewUser.setString(5, emailAddrTextField.getText());
                                insertNewUser.setInt(6, 0);
                                insertNewUser.setInt(7, 0);
                                insertNewUser.setInt(8, 0);
                                insertNewUser.setInt(9, 0);
                                System.out.println(insertNewUser.toString());
                                insertNewUser.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                MSFWindowsTestApplication.conn.commit();
                MSFWindowsTestApplication.conn.close();
                myController.setScreen(FXMLGetResourcer.userAccountCreatedScreenID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
