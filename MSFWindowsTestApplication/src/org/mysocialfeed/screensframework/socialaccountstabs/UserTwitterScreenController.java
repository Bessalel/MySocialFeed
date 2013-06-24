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

/**
 * FXML Controller class
 *
 * @author Vincent
 */
public class UserTwitterScreenController extends ControlledScreen implements Initializable {
    
    @Inject
    public UserTwitterScreenController(){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void goToUserMainScreen(ActionEvent event) {
        this.getScreenController().setScreen(FXMLGetResourcer.userMainScreenID);
    }
}
