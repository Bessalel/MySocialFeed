/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.supportingfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mysocialfeed.models.BuildAndFillDatabase;
import org.mysocialfeed.screensframework.FXMLGetResourcer;

// for SQL :

import org.mysocialfeed.screensframework.ScreensController;

/**
 *
 * @author Vincent
 */
public class MSFWindowsTestApplication extends Application {
 
    public static Connection conn;
    public static Statement stmt;
    
    @Override
    public void start(Stage primaryStage) {
        
        accessAndSetupSQLServer(true);
        
        ScreensController mainController = new ScreensController();
        addAllScreens(mainController);
        
        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void accessAndSetupSQLServer(boolean createTables){
        try {  
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mysocialfeed","root","");
            conn.setAutoCommit(false);
 
            if (createTables == true) { // only needed once at application launch
                stmt = conn.createStatement();

                stmt.execute(BuildAndFillDatabase.CREATE_TABLE_USERS_SQL);
                stmt.execute(BuildAndFillDatabase.CREATE_TABLE_FACEBOOK_SQL);
                stmt.execute(BuildAndFillDatabase.CREATE_TABLE_TWITTER_SQL);
                stmt.execute(BuildAndFillDatabase.CREATE_TABLE_GOOGLEPLUS_SQL);
                stmt.execute(BuildAndFillDatabase.CREATE_TABLE_PINTEREST_SQL);

                stmt.close();
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
     private void addAllScreens(ScreensController mainController) {
       
        FXMLGetResourcer myFXMLGetResourcer = new FXMLGetResourcer();
              
        mainController.loadScreen(FXMLGetResourcer.welcomeScreenID, myFXMLGetResourcer.getWelcomeScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userMainScreenID, myFXMLGetResourcer.getUserMainScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userSignUpScreenID, myFXMLGetResourcer.getUserSignUpScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userAccountCreatedScreenID, myFXMLGetResourcer.getUserAccountCreatedScreenFile());
        
        mainController.loadScreen(FXMLGetResourcer.userAddAccountScreenID, myFXMLGetResourcer.getUserAddAccountScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userFacebookScreenID, myFXMLGetResourcer.getUserFacebookScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userGooglePlusScreenID, myFXMLGetResourcer.getUserGooglePlusScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userPinterestScreenID, myFXMLGetResourcer.getUserPinterestScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userTwitterScreenID, myFXMLGetResourcer.getUserTwitterScreenFile());
        
        mainController.loadScreen(FXMLGetResourcer.addFacebookAccountScreenID, myFXMLGetResourcer.getAddFacebookScreenAccountScreenFile());
        mainController.loadScreen(FXMLGetResourcer.addGooglePlusAccountScreenID, myFXMLGetResourcer.getAddGooglePlusAccountScreenFile());
        mainController.loadScreen(FXMLGetResourcer.addPinterestAccountScreenID, myFXMLGetResourcer.getAddPinterestAccountScreenFile());
        mainController.loadScreen(FXMLGetResourcer.addTwitterAccountScreenID, myFXMLGetResourcer.getAddTwitterAccountScreenFile());
        mainController.loadScreen(FXMLGetResourcer.AccountCreationResultScreenID, myFXMLGetResourcer.getAccountCreationResultScreenFile());
        
        mainController.setScreen(FXMLGetResourcer.welcomeScreenID); 
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}