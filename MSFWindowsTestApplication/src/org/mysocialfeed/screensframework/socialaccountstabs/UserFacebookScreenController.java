/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs;

import com.google.inject.Inject;
import java.awt.Rectangle;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.joda.time.DateTime;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.services.repository.UserDataService;
import org.mysocialfeed.services.repository.UserPostsService;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserFacebookScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    private UserDataService userDataService;
    private UserPostsService userPostsService;
    private int FbAccountID = 1;
    
    @FXML private TextArea userPost = new TextArea();
    
    @FXML private Button resetField = new Button();
    @FXML private Button sendPost = new Button();
    
    @FXML private ScrollPane timeLine = new ScrollPane();
    @FXML private AnchorPane pane = new AnchorPane(); 
    @FXML private VBox chatBox = new VBox();
    
    @Inject
        public UserFacebookScreenController(UserDataService userDataService, UserPostsService userPostsService){
        this.userDataService = userDataService;
        this.userPostsService = userPostsService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDefaultProperties();
    }    
    
    
    private void setDefaultProperties() {
        if (this.userPostsService.hasPost() == true) {
            for (int it = 0; this.userPostsService.getContent(it) != null; it++ ) {
                this.chatBox.getChildren().add(new Label(this.userPostsService.getContent(it)));
            }
        }
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void resetField(ActionEvent e) {
        this.userPost.setText(null);
    }
    
    @FXML
    private void sendPost(ActionEvent e) {
        String cssBordering = "-fx-border-color:darkblue ; \n" //#090a0c
                + "-fx-border-insets:3;\n"
                + "-fx-border-radius:1;\n"
                + "-fx-border-width:2.0";
        
        DateTime dt = new DateTime();
        this.userPostsService.addPost(FbAccountID, "Fb", this.userPost.getText(), dt);
        
        Label currentPost = new Label(this.userPost.getText() + "\n" + dt.toDate() + "\n");

        currentPost.setMaxWidth(Double.MAX_VALUE);
        currentPost.setWrapText(true);
        currentPost.setStyle(cssBordering);
        
        currentPost.setStyle("-fx-background-color: #DCDCDC;");
        this.chatBox.getChildren().add(currentPost);
    }
    
}
