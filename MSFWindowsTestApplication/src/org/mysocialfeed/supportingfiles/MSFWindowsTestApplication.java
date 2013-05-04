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
    
    public static void accessAndSetupSQLServer(){
        try {  
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mysocialfeed","root","");
            conn.setAutoCommit(false);
 
            stmt = conn.createStatement();
            
            stmt.execute(BuildAndFillDatabase.CREATE_TABLE_USERS_SQL);
            stmt.execute(BuildAndFillDatabase.CREATE_TABLE_FACEBOOK_SQL);
            stmt.execute(BuildAndFillDatabase.CREATE_TABLE_TWITTER_SQL);
            stmt.execute(BuildAndFillDatabase.CREATE_TABLE_GOOGLEPLUS_SQL);
            stmt.execute(BuildAndFillDatabase.CREATE_TABLE_PINTEREST_SQL);
            
            stmt.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        accessAndSetupSQLServer();
        
        FXMLGetResourcer myFXMLGetResourcer = new FXMLGetResourcer();
        
        
        ScreensController mainController = new ScreensController();
                
        mainController.loadScreen(FXMLGetResourcer.welcomeScreenID, myFXMLGetResourcer.getWelcomeScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userMainScreenID, myFXMLGetResourcer.getUserMainScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userSignUpScreenID, myFXMLGetResourcer.getUserSignUpScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userAccountCreatedScreenID, myFXMLGetResourcer.getUserAccountCreatedScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userFacebookScreenID, myFXMLGetResourcer.getUserFacebookScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userGooglePlusScreenID, myFXMLGetResourcer.getUserGooglePlusScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userPinterestScreenID, myFXMLGetResourcer.getUserPinterestScreenFile());
        mainController.loadScreen(FXMLGetResourcer.userTwitterScreenID, myFXMLGetResourcer.getUserTwitterScreenFile());
        
        
        mainController.setScreen(FXMLGetResourcer.welcomeScreenID);
        
        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}