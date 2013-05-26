/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.swing.JLabel;
import org.mysocialfeed.models.DatabaseManager;
import org.mysocialfeed.models.Context;
import org.mysocialfeed.models.UserPosts;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class UserFacebookScreenController implements Initializable, ControlledScreen {

    ScreensController myController;

    @FXML
    private Button resetField;
    @FXML
    private Button sendPost;
    @FXML
    private TextArea post;
    @FXML
    public static TextArea timeLine;
    
    @FXML
    private Label successMessage;
    @FXML
    private Label errorMessage;
    
    private static List<Integer> alreadyDisplayed = new ArrayList<Integer>();
    private static Date date;
    private static Timestamp timeStamp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeLine.setText("");
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    private static boolean checkIfAlreadyDisplayed(int index){
        for (int it : alreadyDisplayed){
            if (it == index){
                return false;
            }
        }
        return true;
    }
    
    @FXML
    public static void loadUserFbTimeline(){
        UserPosts temp = Context.getCurrentPosts();
        if (Context.getCurrentPosts() != null) {
            for (int it = 0; it < (Context.getCurrentPosts().getContent().size()); it++){
                if (Context.getCurrentPosts().getAccountType().get(it).compareTo("Fb") == 0 
                        && checkIfAlreadyDisplayed(it) == true){
                    
                    JLabel myTimeStamp = new JLabel(
                            "<u>" + new Date(Context.getCurrentPosts().getTimeStamp().get(it).getTime()) + "</u>");
                    
                    timeLine.setText(timeLine.getText() + "Message wrote at " + new Date(Context.getCurrentPosts().getTimeStamp().get(it).getTime())
                            + "\n\n" + Context.getCurrentPosts().getContent().get(it)
                            + "\n_________________________________________________________________________________________\n\n");
                    alreadyDisplayed.add(it);
                }
            }
        }
    }
    
    @FXML
    private void userTimeline(Event event) {
    }
    
    @FXML
    private void userWriteSomething(Event event) {
        
    }
    
    @FXML
    private void userLeaveTab(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void userResetFieldPost(ActionEvent event) {
        post.setText("");
    }
    
    private boolean insertPostIntoDatabase(){
        try {
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                MSFWindowsTestApplication.accessAndSetupSQLServer(false);
                }
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                
                date = new Date();
                timeStamp = new Timestamp(date.getTime());
                
                try (PreparedStatement addPost = 
                        MSFWindowsTestApplication.conn.prepareStatement(
                        DatabaseManager.INSERT_POST)) {
                            addPost.setInt(1, Context.getCurrentUser().getUserID());
                            addPost.setInt(2, 5); // Later must add dynamically account id
                            addPost.setString(3, "Fb");
                            addPost.setString(4, post.getText());
                            addPost.setTimestamp(5, timeStamp);
                            System.out.println(addPost.toString());
                            addPost.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
                MSFWindowsTestApplication.conn.commit();
                MSFWindowsTestApplication.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @FXML
    private void userSendPost(ActionEvent event) {
        if (insertPostIntoDatabase()){
            // Need to update Context as well !
            Context.getCurrentPosts().addAccountID(5); // Warning : later must add dynamically account id !!
            Context.getCurrentPosts().addAccountType("Fb");
            Context.getCurrentPosts().addPost(post.getText());
            Context.getCurrentPosts().addTimeStamp(timeStamp);
        }
    }
}
