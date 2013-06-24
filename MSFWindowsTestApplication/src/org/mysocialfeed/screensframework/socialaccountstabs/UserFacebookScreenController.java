/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs;

import com.google.inject.Inject;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.joda.time.DateTime;

import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.services.repository.UserDataService;
import org.mysocialfeed.services.repository.UserPostsService;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserFacebookScreenController extends ControlledScreen implements Initializable {
    
    private final UserDataService userDataService;
    private final UserPostsService userPostsService;
    private final int FbAccountID = 1; // need to be dynamically implemented
    private String backGroundStyle = new String();
    
    @FXML private TextArea userPost = new TextArea();
    @FXML private ScrollPane sp = new ScrollPane();
    @FXML private VBox vb = new VBox();
    
    @Inject
        public UserFacebookScreenController(UserDataService userDataService, UserPostsService userPostsService){
        this.userDataService = userDataService;
        this.userPostsService = userPostsService;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDefaultProperties();
    }    
    
    
    private Label formatMessage(String message, DateTime dt) {
        
        DateTime.Property pDoW = dt.dayOfWeek();
        DateTime.Property pMoY = dt.monthOfYear();
        Label temp = new Label(message + "\n\nwrote on " + pDoW.getAsText(Locale.ENGLISH)
                + " " + pMoY.getAsText(Locale.ENGLISH) + " " + dt.getYear()
                + " by " + this.userDataService.getUserName() + "\n\n");
        
        if (this.backGroundStyle.isEmpty() || this.backGroundStyle == "-fx-background-color: #F8F8FF;") {
            backGroundStyle = "-fx-background-color: #DCDCDC;";
        } else if (this.backGroundStyle == "-fx-background-color: #DCDCDC;") {
            this.backGroundStyle = "-fx-background-color: #F8F8FF;";
        }
        
        temp.setStyle(this.backGroundStyle);
        temp.setMinWidth(this.vb.getMinWidth());
        return temp;
    }
    
    private void addMessagesToTimeline(int iterator) {
        for (int it = iterator ; it > 0; it-- ) { // to display messages from most recent to oldest
                this.vb.getChildren().add(formatMessage(
                        this.userPostsService.getContent(it), this.userPostsService.getTimeStamp(it)));
            }
    }
    
    private void setDefaultProperties() {
        this.sp.setVmax(440);
        this.sp.setPrefSize(690, 685);
        this.vb.setMinWidth(this.sp.getPrefWidth());
        if (this.userPostsService.hasPost() == true) {
            addMessagesToTimeline(this.userPostsService.getMaxIndex() - 1);
            this.sp.setContent(this.vb);
        }
    }
    
    @FXML
    private void resetField(ActionEvent e) {
        this.userPost.setText(null);
    }
    
    @FXML
    private void sendPost(ActionEvent e) { 
        DateTime dt = new DateTime();
        this.userPostsService.addPost(FbAccountID, "Fb", this.userPost.getText(), dt);
        this.vb.getChildren().add(0, formatMessage(this.userPost.getText(), dt));
        this.sp.setContent(this.vb);
        this.userPost.setText("");
    }
    
    @FXML
    private void refreshPosts(ActionEvent e) {
        int newIndex = this.userPostsService.refreshUserPosts();
        if (newIndex != -1 ) {
            this.addMessagesToTimeline(newIndex);
        }
    }
    
    @FXML
    private void leaveFacebookTab(ActionEvent e) {
        this.getScreenController().setScreen(FXMLGetResourcer.userMainScreenID);
    }
}
