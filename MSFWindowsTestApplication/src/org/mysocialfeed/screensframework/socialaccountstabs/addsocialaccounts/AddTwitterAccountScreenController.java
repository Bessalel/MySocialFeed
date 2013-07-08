/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs.addsocialaccounts;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.services.socialservices.TwitterService;

/**
 * FXML Controller class
 *
 * @author Windows
 */

public class AddTwitterAccountScreenController extends ControlledScreen implements Initializable {
   
    @FXML private TextField pin = new TextField();
    @FXML private Hyperlink url = new Hyperlink();
    
    @FXML private Label successMsg1 = new Label();
    @FXML private Label successMsg2 = new Label();
    @FXML private Button goToTimeline = new Button();
    @FXML private Button goToMainPage = new Button();
    
    @FXML private Button hideOrShow = new Button();
    
    @FXML private Label failureMsg1 = new Label();
    @FXML private Label failureMsg2 = new Label();
    
    @FXML private Label emptyPin = new Label();
    
    @FXML private final WebView wv = new WebView();
    @FXML private final WebEngine wb = wv.getEngine();
    
    @FXML private StackPane stackPane = new StackPane();
    
    private boolean status;
    private final TwitterService twitterService;

    
    @Inject
    public AddTwitterAccountScreenController(TwitterService twitterService){
        this.twitterService = twitterService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setDefaultProperties();
    }
    
    private void setDefaultProperties() {
        this.url.setText(this.twitterService.setUpAuthentication());
        System.out.println(this.url.getText());
        this.wv.setVisible(true);
        this.wb.setJavaScriptEnabled(true);
        this.wb.load(this.url.getText());
        this.stackPane.getChildren().add(wv);
        
        this.successMsg1.setVisible(false);
        this.successMsg2.setVisible(false);
        this.goToTimeline.setVisible(false);
        this.goToMainPage.setVisible(false);
        this.failureMsg1.setVisible(false);
        this.failureMsg2.setVisible(false);
        
        this.status = true;
    }
    
    @Override
    protected void onActivated() {
        if (this.status == false) {
            setDefaultProperties();
        }
    }
    
    @Override
    protected void onDeActivated() {
        this.stackPane.getChildren().remove(this.wv);
        this.wb.load(null);
        this.url.setText(null);
        this.successMsg1.setVisible(false);
        this.successMsg2.setVisible(false);
        this.goToTimeline.setVisible(false);
        this.goToMainPage.setVisible(false);
        this.failureMsg1.setVisible(false);
        this.failureMsg2.setVisible(false);
        this.status = false;
    }
    
    @FXML
    private void authenticate(ActionEvent e) {
        if (this.pin.getText().isEmpty() || this.pin.getText().length() != 7) {
            this.emptyPin.setVisible(true);
        } else {
            this.emptyPin.setVisible(false);
            this.twitterService.setPin(this.pin.getText());
            
            if (this.twitterService.authenticate()) {
                this.successMsg1.setVisible(true);
                this.successMsg2.setVisible(true);
                this.goToTimeline.setVisible(true);
                this.goToMainPage.setVisible(true);
          } else {
                this.url.setText(this.twitterService.setUpAuthentication());
                this.failureMsg1.setVisible(true);
                this.failureMsg2.setVisible(true);
            }
        }
    }
    
    @FXML
    private void hideOrShowWebBrowser(ActionEvent e) {
        if (this.wv.isVisible()) {
            this.wv.setVisible(false);
            this.hideOrShow.setText("Show web browser");
        } else
            this.wv.setVisible(true);
            this.hideOrShow.setText("Done");
    }
    
    @FXML
    private void goToTimeline(ActionEvent e) {
        this.getScreenController().setScreen(FXMLGetResourcer.userTwitterScreenID);
    }
    
    @FXML
    private void goToMainPage(ActionEvent e) {
        this.getScreenController().setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void goBack(ActionEvent e) {
        this.getScreenController().setScreen((FXMLGetResourcer.userAddAccountScreenID));
    }
}
