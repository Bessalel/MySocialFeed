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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserAddAccountScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @FXML private final Label gPlusNotSupported = new Label();
    @FXML private final Label twNotSupported = new Label();
    @FXML private final Label pinNotSupported = new Label();
    
    
    @Inject
    public UserAddAccountScreenController(){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    
    @FXML
    public void addFacebookAccount(MouseEvent event) {
        myController.setScreen(FXMLGetResourcer.addFacebookAccountScreenID);
    }
    
    @FXML
    private void addTwitterAccount(MouseEvent event) {
        this.twNotSupported.setVisible(false);
       // myController.setScreen(FXMLGetResourcer.addTwitterAccountScreenID);
    }
    
    @FXML
    private void addGooglePlusAccount(MouseEvent event) {
        this.gPlusNotSupported.setVisible(false);
        // myController.setScreen(FXMLGetResourcer.addGooglePlusAccountScreenID);
    }
    
    @FXML
    private void addPinterestAccount(MouseEvent event) {
        this.pinNotSupported.setVisible(false);
       // myController.setScreen(FXMLGetResourcer.addPinterestAccountScreenID);
    }
    
    @FXML
    private void backToUserMainScreen(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void signUserOff(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
     //   UserMainScreenController.welcomeMessage.setText(
       //         UserMainScreenController.welcomeMessage.getText().substring(0, 7)); // remove previous added username
       // WelcomeScreenController.errorMessage.setVisible(false);
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
