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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mysocialfeed.services.interfaces.UserService;

/**
 *
 * @author Vincent
 */
public class WelcomeScreenController extends ControlledScreen implements Initializable {
    
    private final UserService userService;
    
    @Inject
    public WelcomeScreenController(UserService userService){
        this.userService = userService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
    
    @FXML private final Label errorMessage = new Label();
    @FXML private final TextField userName = new TextField();
    @FXML private final PasswordField userPassword = new PasswordField();

    @Override
    protected void onDeActivated() {
        this.userName.setText(null);
        this.userPassword.setText(null);
    }
    
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
                this.getScreenController().setScreen(FXMLGetResourcer.userMainScreenID);
            }
        }     
    }
    
    @FXML
    private void SignUserUp(ActionEvent event) {
        this.getScreenController().setScreen(FXMLGetResourcer.userSignUpScreenID);
    }
}
