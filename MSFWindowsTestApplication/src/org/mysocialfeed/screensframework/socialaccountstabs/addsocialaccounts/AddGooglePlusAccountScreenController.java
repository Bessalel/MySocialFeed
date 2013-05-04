/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs.addsocialaccounts;

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
public class AddGooglePlusAccountScreenController implements Initializable, ControlledScreen {

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
}
