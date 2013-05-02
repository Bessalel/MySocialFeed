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
public class UserTwitterTabScreenController implements Initializable, ControlledScreen {

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
    private void goToUserMainScreen(ActionEvent event) {
        myController.setScreen(MSFWindowsTestApplication.userMainScreenID);
        System.out.println("going to user main screen");
    }
}
