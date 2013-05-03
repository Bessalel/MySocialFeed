/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.mysocialfeed.models.Context;

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserMainScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    @FXML
    public static Label welcomeMessage;
    
    @FXML
    public static ListView socialAccountList;
    
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
    private void userChooseTwitter(ActionEvent event) {
        myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
    }
    
    @FXML
    private void logUserOff(ActionEvent event) {
        myController.setScreen(MSFWindowsTestApplication.welcomeScreenID);
    }
}
