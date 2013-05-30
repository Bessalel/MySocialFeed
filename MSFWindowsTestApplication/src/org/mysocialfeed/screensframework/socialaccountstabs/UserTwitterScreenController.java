/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserTwitterScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @Inject
    public UserTwitterScreenController(){
        System.out.println("UTSC is OK");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    
    @FXML
    private void goToUserMainScreen(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
}
