/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.mysocialfeed.models.BuildAndFillDatabase;
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserSignUpScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @FXML Label accountCreatedOKMessage;
    
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
        //MSFWindowsTestApplication.
        ;
    }    

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public void createUserAccount(ActionEvent event) {
        try {
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                System.out.println("accessing SQL");
                MSFWindowsTestApplication.accessSQL();
                }
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                System.out.println("inserting...");
                    try (PreparedStatement insertNewUser = 
                            MSFWindowsTestApplication.conn.prepareStatement(
                            BuildAndFillDatabase.INSERT_USER, 
                            Statement.RETURN_GENERATED_KEYS)) {
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
                System.out.println("inserting done ! And now closing...");
                MSFWindowsTestApplication.conn.close();
                myController.setScreen(MSFWindowsTestApplication.userAccountCreatedScreenID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void returnUserBackToWelcomeScreen(ActionEvent event) {
        myController.setScreen(MSFWindowsTestApplication.welcomeScreenID);
    }
    
}
