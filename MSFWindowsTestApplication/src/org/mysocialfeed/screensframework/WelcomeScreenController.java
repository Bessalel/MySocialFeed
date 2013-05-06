/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.stage.Stage;
import org.mysocialfeed.models.BuildAndFillDatabase;
import org.mysocialfeed.models.Context;
import org.mysocialfeed.models.UserData;

/**
 *
 * @author Vincent
 */
public class WelcomeScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
    public void setScreenParent(ScreensController screenParent){   
        myController = screenParent;
    }
    
    @FXML
    public static Label errorMessage;
    
    @FXML
    TextField userName;
    
    @FXML
    PasswordField userPassword;

    @FXML
    private void SignUserIn(ActionEvent event) {
        try {
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                MSFWindowsTestApplication.accessAndSetupSQLServer(false);
                }
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                try(PreparedStatement getUserData = 
                        MSFWindowsTestApplication.conn.prepareStatement(
                        BuildAndFillDatabase.LIST_USER, 
                        Statement.RETURN_GENERATED_KEYS)) {
                    getUserData.setString(1, userName.getText());
                    ResultSet rs = getUserData.executeQuery();
                    if (!(rs.next())) {
                        errorMessage.setVisible(true);
                    } else {
                            if ((userName.getText().compareTo(rs.getString(2)) == 0) 
                             && (userPassword.getText().compareTo(rs.getString(3)) == 0)){
                                UserData currentUser = new UserData(true, rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getInt(7), rs.getBoolean(8), rs.getInt(8), rs.getBoolean(9), rs.getInt(9), rs.getBoolean(10), rs.getInt(10));
                                rs.close();
                                System.out.println(currentUser.toString());
                                Context.setCurrentUser(currentUser);
                                if (Context.getCurrentUser() != null) {
                                     UserMainScreenController.welcomeMessage.setText(UserMainScreenController.welcomeMessage.getText()
                                             + " " + Context.getCurrentUser().getUserFirstName() + " !");
                                     
                                     if (Context.getCurrentUser() != null) {
                                           if (!(Context.getCurrentUser().hasFacebook())
                                                   && !(Context.getCurrentUser().hasTwitter())
                                                   && !(Context.getCurrentUser().hasGooglePlus())
                                                   && !(Context.getCurrentUser().hasPinterest())) {

                                               UserMainScreenController.addFirstAccount.setVisible(true);
                                               
                                               UserMainScreenController.userHasAccount.setVisible(false);
                                               UserMainScreenController.noAccountAvailable.setVisible(true);
                                               UserMainScreenController.accessFacebook.setVisible(false);
                                               UserMainScreenController.accessTwitter.setVisible(false);
                                               UserMainScreenController.accessGooglePlus.setVisible(false);
                                               UserMainScreenController.accessPinterest.setVisible(false);
                                               UserMainScreenController.addAccount.setVisible(false);
                                           } else {
                                               UserMainScreenController.addAccount.setVisible(true);
                                           }
                                        }
                                   }
                                myController.setScreen(FXMLGetResourcer.userMainScreenID);
                            } else if ((userName.getText().compareTo(rs.getString(2)) != 0) 
                                    || (userPassword.getText().compareTo(rs.getString(3)) != 0)){
                                errorMessage.setVisible(true);
                        }
                    }
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
                MSFWindowsTestApplication.conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        } 
    }
    
    @FXML
    private void SignUserUp(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(600);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.userSignUpScreenID);
    }
}
