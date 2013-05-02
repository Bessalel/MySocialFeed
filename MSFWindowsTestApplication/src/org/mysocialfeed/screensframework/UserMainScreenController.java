/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserMainScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;

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
    private void userChooseTwitter(ActionEvent event) {
        myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
        System.out.println("going to twitter tab");
    }
    
    @FXML
    private void logUserOff(ActionEvent event) {
        myController.setScreen(MSFWindowsTestApplication.welcomeScreenID);
        System.out.println("going to welcome screen");
    }
}
