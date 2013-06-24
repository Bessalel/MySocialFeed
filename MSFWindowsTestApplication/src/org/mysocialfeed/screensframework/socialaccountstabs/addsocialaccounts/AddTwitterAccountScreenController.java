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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.mysocialfeed.screensframework.ControlledScreen;

/**
 * FXML Controller class
 *
 * @author Windows
 */

public class AddTwitterAccountScreenController extends ControlledScreen implements Initializable {
   
    @FXML private final WebView browser = new WebView();
    @FXML private final BorderPane bp = new BorderPane();
    @FXML private final AnchorPane ap = new AnchorPane();
    
    private WebEngine webEngine = new WebEngine();
    
    
    @Inject
    public AddTwitterAccountScreenController(){
        this.webEngine = this.browser.getEngine();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    @FXML
    private void load(ActionEvent e) {
         this.webEngine.load("http://www.google.fr");
         this.webEngine.setJavaScriptEnabled(true);
         this.ap.getChildren().add(this.browser);
         System.out.println("LOADING !! MATHAFUCKER");
    }
}
