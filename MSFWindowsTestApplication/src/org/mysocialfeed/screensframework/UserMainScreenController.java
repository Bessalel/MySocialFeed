/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserMainScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    @FXML
    public static Label welcomeMessage;
    
  //  @FXML
  //  public static ListView socialAccountList;
    
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
    private void userChooseFacebook(ActionEvent event) {
        // myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
    }
    
    @FXML
    private void userChooseTwitter(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.userTwitterScreenID);
    }
    
    @FXML
    private void userChooseGooglePlus(ActionEvent event) {
      //  myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
    }
    
    @FXML
    private void userChoosePinterest(ActionEvent event) {
      //  myController.setScreen(MSFWindowsTestApplication.userTwitterTabScreenID);
    }
    
    
    @FXML
    private void logUserOff(ActionEvent event) {
        UserMainScreenController.welcomeMessage.setText(UserMainScreenController.welcomeMessage.getText().substring(0, 7));
        
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
