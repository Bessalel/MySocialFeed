/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.screensframework.UserMainScreenController;
import org.mysocialfeed.screensframework.WelcomeScreenController;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserAddAccountScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    public static boolean result = false;
    
    @FXML
    public void addFacebookAccount(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(615);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
        myController.setScreen(FXMLGetResourcer.addFacebookAccountScreenID);
    }
    
    @FXML
    private void addTwitterAccount(MouseEvent event) {
        myController.setScreen(FXMLGetResourcer.addTwitterAccountScreenID);
    }
    
    @FXML
    private void addGooglePlusAccount(MouseEvent event) {
        myController.setScreen(FXMLGetResourcer.addGooglePlusAccountScreenID);
    }
    
    @FXML
    private void addPinterestAccount(MouseEvent event) {
        myController.setScreen(FXMLGetResourcer.addPinterestAccountScreenID);
    }
    
    @FXML
    private void backToUserMainScreen(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void signUserOff(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(515);
        stage.setHeight(440);
        UserMainScreenController.welcomeMessage.setText(
                UserMainScreenController.welcomeMessage.getText().substring(0, 7)); // remove previous added username
        WelcomeScreenController.errorMessage.setVisible(false);
        myController.setScreen(FXMLGetResourcer.welcomeScreenID);
    }
}
