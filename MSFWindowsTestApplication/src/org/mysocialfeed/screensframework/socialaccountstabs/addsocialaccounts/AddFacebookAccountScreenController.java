/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs.addsocialaccounts;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.mysocialfeed.models.BuildAndFillDatabase;
import org.mysocialfeed.models.Context;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class AddFacebookAccountScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    // Text fields :
    @FXML
    private TextField userFirstName;
    @FXML
    private TextField userLastName;
    @FXML
    private TextField userEmailAddr;
    
    // Error messages :
    @FXML
    private Label noFirstName; 
    @FXML
    private Label noLastName;
    @FXML
    private Label noEmailAddr;
    
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    private void userCancelled(ActionEvent event) {
        myController.setScreen(FXMLGetResourcer.userMainScreenID);
    }
    
    @FXML
    private void addUserFacebookAccount(ActionEvent event) {
        noFirstName.setVisible(false);
        noLastName.setVisible(false);
        noEmailAddr.setVisible(false);
        
        if (userFirstName.getText().isEmpty()) {
            noFirstName.setVisible(true);
        } 
        if (userLastName.getText().isEmpty()) {
            noLastName.setVisible(true);
        } 
        if (userEmailAddr.getText().isEmpty()) {
            noEmailAddr.setVisible(true);
        } else if (
                   !(userFirstName.getText().isEmpty())
                && !(userLastName.getText().isEmpty())
                && !(userEmailAddr.getText().isEmpty())) {
                insertUserFacebookAccountIntoDatabase();
        } else
            return ;
    }
    
    private void insertUserFacebookAccountIntoDatabase() {
        try {
            while (MSFWindowsTestApplication.conn.isClosed() == true) {
                MSFWindowsTestApplication.accessAndSetupSQLServer(false);
                }
            if (!(MSFWindowsTestApplication.conn.isClosed())){
                    try (PreparedStatement insertNewFacebookAccount = 
                            MSFWindowsTestApplication.conn.prepareStatement(
                            BuildAndFillDatabase.INSERT_USER)) {
                                insertNewFacebookAccount.setInt(1, Context.getCurrentUser().getUserID());
                                insertNewFacebookAccount.setString(2, userFirstName.getText());
                                insertNewFacebookAccount.setString(3, userLastName.getText());
                                insertNewFacebookAccount.setString(4, userEmailAddr.getText());
                                System.out.println(insertNewFacebookAccount.toString());
                                insertNewFacebookAccount.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                MSFWindowsTestApplication.conn.commit();
                MSFWindowsTestApplication.conn.close();
                AccountSuccessfullyAddedScreenController.successMessage.setText(
                        AccountSuccessfullyAddedScreenController.successMessage.getText() + " facebook account !");
                myController.setScreen(FXMLGetResourcer.accountSuccessfullyAddedScreenID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
