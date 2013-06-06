/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserGooglePlusScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @Inject
    public UserGooglePlusScreenController(){
        System.out.println("UGPSC is OK");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
}
