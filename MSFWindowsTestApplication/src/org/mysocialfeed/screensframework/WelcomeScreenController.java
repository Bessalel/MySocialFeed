/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    
    @FXML
    private Label errorMessage;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;

    
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(600);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.userSignUpScreenID);
    }
}
