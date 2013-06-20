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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserAddAccountScreenController extends ControlledScreen implements Initializable {

    @FXML private final Label gPlusNotSupported = new Label();
    @FXML private final Label twNotSupported = new Label();
    @FXML private final Label pinNotSupported = new Label();
    
    
    @Inject
    public UserAddAccountScreenController(){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
    }  
    
    @FXML
    public void addFacebookAccount(MouseEvent event) {
        this.getScreenController().setScreen(FXMLGetResourcer.addFacebookAccountScreenID);
    }
    
    @FXML
    private void addTwitterAccount(MouseEvent event) {
        this.twNotSupported.setVisible(false);
       // this.getScreenController().setScreen(FXMLGetResourcer.addTwitterAccountScreenID);
    }
    
    @FXML
    private void addGooglePlusAccount(MouseEvent event) {
        this.gPlusNotSupported.setVisible(false);
        // this.getScreenController().setScreen(FXMLGetResourcer.addGooglePlusAccountScreenID);
    }
    
    @FXML
    private void addPinterestAccount(MouseEvent event) {
        this.pinNotSupported.setVisible(false);
       // this.getScreenController().setScreen(FXMLGetResourcer.addPinterestAccountScreenID);
    }
    
    @FXML
    private void backToUserMainScreen(ActionEvent event) {
        this.getScreenController().setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void signUserOff(ActionEvent event) {
        this.getScreenController().setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
