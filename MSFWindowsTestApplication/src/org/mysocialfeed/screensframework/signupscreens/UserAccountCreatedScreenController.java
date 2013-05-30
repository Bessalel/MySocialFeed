/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.signupscreens;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.screensframework.WelcomeScreenController;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserAccountCreatedScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @Inject
    public UserAccountCreatedScreenController(){
        System.out.println("USACSC is OK");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){   
        myController = screenParent;
    }

    @FXML
    public void returnUserBackToWelcomeScreen(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
      //  WelcomeScreenController.errorMessage.setVisible(false);
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
