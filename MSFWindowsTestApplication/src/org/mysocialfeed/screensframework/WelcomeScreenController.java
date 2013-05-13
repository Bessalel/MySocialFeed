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
import org.mysocialfeed.models.UserPosts;
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
    public static Label errorMessage2;
    
    @FXML
    TextField userName;
    
    @FXML
    PasswordField userPassword;

    private boolean loadUserData() {
        try {
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
                                        } else {
                                               UserMainScreenController.userHasAccount.setVisible(true);
                                               UserMainScreenController.noAccountAvailable.setVisible(false);
                                               UserMainScreenController.accessFacebook.setVisible(true);
                                               UserMainScreenController.accessTwitter.setVisible(true);
                                               UserMainScreenController.accessGooglePlus.setVisible(true);
                                               UserMainScreenController.accessPinterest.setVisible(true);
                                               UserMainScreenController.addAccount.setVisible(true);
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
                    return false;
                }
            }
                MSFWindowsTestApplication.conn.close();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean loadContent() {
        try {
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                try(PreparedStatement getPosts = 
                        MSFWindowsTestApplication.conn.prepareStatement(
                        BuildAndFillDatabase.LIST_USER)) {
                            getPosts.setInt(1, Context.getCurrentUser().getUserID());
                    ResultSet rs = getPosts.executeQuery();
                    if (!(rs.next())) {
                        errorMessage2.setVisible(true);
                    } else {
                        UserPosts currentPosts = new UserPosts(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                                rs.close();
                                Context.setCurrentPosts(currentPosts);
                    }
                } catch(SQLException e){
                    e.printStackTrace();
                    return false;
                }
            }
                MSFWindowsTestApplication.conn.close();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean loadData() {
        boolean result = true;
        result = loadUserData();
        if (result == true) {
            result = loadContent();
        }
        return result;
    }
    
    @FXML
    private void SignUserIn(ActionEvent event) {
        try {
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                MSFWindowsTestApplication.accessAndSetupSQLServer(false);
            }
        } catch (SQLException e){
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
