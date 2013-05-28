/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import com.google.inject.Inject;
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mysocialfeed.models.DatabaseManager;
import org.mysocialfeed.models.Context;
import org.mysocialfeed.models.UserPosts;
import org.mysocialfeed.models.UserData;
import org.mysocialfeed.supportingfiles.UserService;

/**
 *
 * @author Vincent
 */
public class WelcomeScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    //Our constructor to implement Injection Dependancies
    
    private final UserService userService;
    
    @Inject
    public WelcomeScreenController(UserService userService){
        this.userService = userService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
    public void setScreenParent(ScreensController screenParent){   
        myController = screenParent;
    }
    
    @FXML
    public static Label errorMessage;
    
    @FXML
    private TextField userName;
    
    @FXML
    private PasswordField userPassword;

    private boolean loadUserData() {
        try {
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                try(PreparedStatement getUserData = 
                        MSFWindowsTestApplication.conn.prepareStatement(
                        DatabaseManager.LIST_USER, 
                        Statement.RETURN_GENERATED_KEYS)) {
                            getUserData.setString(1, userName.getText());
                    ResultSet rs = getUserData.executeQuery();
                    if (!(rs.next())) {
                        errorMessage.setVisible(true);
                        return false;
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
                                               UserMainScreenController.addFirstAccount.setVisible(false);
                                               UserMainScreenController.userHasAccount.setVisible(true);
                                               UserMainScreenController.noAccountAvailable.setVisible(false);
                                               UserMainScreenController.accessFacebook.setVisible(true);
                                               UserMainScreenController.accessTwitter.setVisible(true);
                                               UserMainScreenController.accessGooglePlus.setVisible(true);
                                               UserMainScreenController.accessPinterest.setVisible(true);
                                               UserMainScreenController.addAccount.setVisible(true);
                                           }
                                     }
                                   }
                                myController.setScreen(FXMLGetResourcer.userMainScreenID);
                            } else if ((userName.getText().compareTo(rs.getString(2)) != 0) 
                                    || (userPassword.getText().compareTo(rs.getString(3)) != 0)){
                                errorMessage.setVisible(true);
                                return false;
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
                        DatabaseManager.LIST_ALL_POSTS)) {
                            getPosts.setInt(1, Context.getCurrentUser().getUserID());
                    ResultSet rs = getPosts.executeQuery();
                    if (!(rs.next())) {
                        return true;
                    } else {
                        List<Integer> accountIDTemp = new ArrayList<>();
                        List<String> accountTypeTemp = new ArrayList<>();
                        List<String> contentTemp = new ArrayList<>();
                        List<Timestamp> timeStampTemp = new ArrayList<>();
                        
                        accountIDTemp.add(0, rs.getInt(3));
                        accountTypeTemp.add(0, rs.getString(4));
                        contentTemp.add(0, rs.getString(5));
                        timeStampTemp.add(0, rs.getTimestamp(6));
                        
                        int iterator = 1;
                        while (rs.next()) {
                            accountIDTemp.add(iterator, rs.getInt(3));
                            accountTypeTemp.add(iterator, rs.getString(4));
                            contentTemp.add(iterator, rs.getString(5));
                            timeStampTemp.add(iterator, rs.getTimestamp(6));
                            iterator++;
                        }
                        rs.close();
                        UserPosts currentPosts = new UserPosts(
                                Context.getCurrentUser().getUserID(), 
                                accountIDTemp, accountTypeTemp, 
                                contentTemp, timeStampTemp);
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
        if (loadUserData() == true){
            try {
                while (MSFWindowsTestApplication.conn.isClosed() == true) {
                    MSFWindowsTestApplication.accessAndSetupSQLServer(false);
                }
                return loadContent();
            }catch(SQLException e){
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    @FXML
    private void SignUserIn(ActionEvent event) {
        try {
            if (userName.getText().isEmpty() && userPassword.getText().isEmpty()) {
                errorMessage.setVisible(true);
                return;
            }
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                MSFWindowsTestApplication.accessAndSetupSQLServer(false);
            }
            if (loadData() == true) {
                myController.setScreen(FXMLGetResourcer.userMainScreenID);
            }else {
                //
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
