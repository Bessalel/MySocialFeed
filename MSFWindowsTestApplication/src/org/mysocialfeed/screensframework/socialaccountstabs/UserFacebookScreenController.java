/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.mysocialfeed.models.BuildAndFillDatabase;
import org.mysocialfeed.models.Context;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserFacebookScreenController implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private static Button resetField;
    
    @FXML
    private static Button sendPost;
    
    @FXML
    private static TextField post;
    
    
    @FXML
    private static Label successMessage;
    @FXML
    private static Label errorMessage;
    
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
    private void userTimeline(ActionEvent event) {
        resetField.setVisible(false);
        sendPost.setVisible(false);
    }
    
    @FXML
    private void userWriteSomething(ActionEvent event) {
        resetField.setVisible(true);
        sendPost.setVisible(true);
    }
    
    @FXML
    private void userLeaveTab(ActionEvent event) {
        resetField.setVisible(false);
        sendPost.setVisible(false);
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void userResetFieldPost(ActionEvent event) {
        post.setText("");
    }
    
    @FXML
    private boolean userSendPost(ActionEvent event) {
        try {
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                MSFWindowsTestApplication.accessAndSetupSQLServer(false);
                }
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                
                try (PreparedStatement addPost = 
                        MSFWindowsTestApplication.conn.prepareStatement(
                        BuildAndFillDatabase.INSERT_POST)) {
                            addPost.setInt(1, Context.getCurrentUser().getUserID());
                            addPost.setInt(2, 1); // Later must add dynamically account id
                            addPost.setString(3, "Fb");
                            addPost.setString(4, post.getText());
                            System.out.println(addPost.toString());
                            addPost.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
                MSFWindowsTestApplication.conn.commit();
                MSFWindowsTestApplication.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
