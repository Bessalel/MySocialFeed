/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import com.google.inject.Inject;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.mysocialfeed.services.interfaces.UserService;

/**
 *
 * @author Vincent
 */
public class WelcomeScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    //Our constructor to implement Injection Dependancies
    
    private final UserService userService;
    
    @Inject
    public WelcomeScreenController(UserService userService){
        this.userService = userService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
    
    @Override
    public void setScreenParent(ScreensController screenParent){   
        myController = screenParent;
    }
    
    @FXML private Label errorMessage = new Label();
    @FXML private TextField userName = new TextField();
    @FXML private PasswordField userPassword = new PasswordField();

    
    @FXML
    private void SignUserIn(ActionEvent event) {
        if (userName.getText().isEmpty() && userPassword.getText().isEmpty()) {
            this.errorMessage.setText("You must provide a valid \nUsername AND Password!");
            this.errorMessage.setVisible(true);
        } else {
            if (this.userService.authenticate(userName.getText(), userPassword.getText()) == false) {
                this.errorMessage.setText("Error : something went wrong or \nyour Username and/or Password do not match !");
                this.errorMessage.setVisible(true);
            } else {
                this.myController.setScreen(FXMLGetResourcer.userMainScreenID);
            }
        }     
    }
    
    @FXML
    private void SignUserUp(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.userSignUpScreenID);
    }
}
