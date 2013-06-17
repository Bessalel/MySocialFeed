/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework.socialaccountstabs.addsocialaccounts;

import com.google.inject.Inject;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mysocialfeed.models.DatabaseManager;
import org.mysocialfeed.models.Context;
import org.mysocialfeed.screensframework.ControlledScreen;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.screensframework.socialaccountstabs.UserAddAccountScreenController;
import org.mysocialfeed.supportingfiles.MSFWindowsTestApplication;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class AddFacebookAccountScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @Inject
    public AddFacebookAccountScreenController(){
        System.out.println("AFSC is OK");
    }
    
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
    
    @FXML
    private void userCancelled(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setWidth(605);
        stage.setHeight(430);
        myController.setScreen(FXMLGetResourcer.userAddAccountScreenID);
    }
    
    @FXML
    private void addUserFacebookAccount(ActionEvent event) {

    }
    
    
  //  private void insertUserFacebookAccountIntoDatabase() {
  //      try {
  //          while (MSFWindowsTestApplication.conn.isClosed() == true) {
  //              MSFWindowsTestApplication.accessAndSetupSQLServer(false);
  //              }
  //          if (!(MSFWindowsTestApplication.conn.isClosed())){
  //                  try (PreparedStatement insertNewFacebookAccount = 
  //                          MSFWindowsTestApplication.conn.prepareStatement(
  //////                          DatabaseManager.INSERT_FACEBOOK_ACCOUNT)) {
  ////                              insertNewFacebookAccount.setInt(1, Context.getCurrentUser().getUserID());
  //                              insertNewFacebookAccount.setString(2, userFirstName.getText());
  //                              insertNewFacebookAccount.setString(3, userLastName.getText());
  //                              insertNewFacebookAccount.setString(4, userEmailAddr.getText());
  ////                              System.out.println(insertNewFacebookAccount.toString());
  //                              insertNewFacebookAccount.execute();
  ////                              UserAddAccountScreenController.result = true;
  //                  } catch (SQLException e) {
  //                      UserAddAccountScreenController.result = false;
  //                      e.printStackTrace();
  //                  }
  //              MSFWindowsTestApplication.conn.commit();
  //              MSFWindowsTestApplication.conn.close();
  //              if (UserAddAccountScreenController.result == true) {
  //                  UserAddAccountScreenController.result = updateUserData();
  //                  if (UserAddAccountScreenController.result == true) { //if only both queries worked !
  //                      AccountCreationResultScreenController.retry.setVisible(false);
  //                      AccountCreationResultScreenController.cancel.setVisible(false);
//
  //                      AccountCreationResultScreenController.addAnotherAccount.setVisible(true);
  //                      AccountCreationResultScreenController.addAnotherFbAccount.setVisible(true);
  //                      AccountCreationResultScreenController.mainPage.setVisible(true);
//
  //                      AccountCreationResultScreenController.failureMessage.setVisible(false);
  //                      AccountCreationResultScreenController.successMessage1.setVisible(true);
  //                      AccountCreationResultScreenController.successMessage2.setVisible(true);
  //                      AccountCreationResultScreenController.successMessage2.setText(
  //                      AccountCreationResultScreenController.successMessage2.getText() + " facebook account !");
  //                  }
  //              } 
  //              if (UserAddAccountScreenController.result == false) {
  //                  AccountCreationResultScreenController.retry.setVisible(true);
  ////                  AccountCreationResultScreenController.cancel.setVisible(true);
                    
  //                  AccountCreationResultScreenController.addAnotherAccount.setVisible(false);
  //                  AccountCreationResultScreenController.addAnotherFbAccount.setVisible(false);
  //                  AccountCreationResultScreenController.mainPage.setVisible(false);
  //                  
  //                  AccountCreationResultScreenController.failureMessage.setVisible(true);
  //                  AccountCreationResultScreenController.successMessage1.setVisible(false);
  //                  AccountCreationResultScreenController.successMessage2.setVisible(false);
  //              }
  //              myController.setScreen(FXMLGetResourcer.AccountCreationResultScreenID);
  //          }
  //      } catch (SQLException e) {
  ////          e.printStackTrace();
  //      }
  //  }
}
