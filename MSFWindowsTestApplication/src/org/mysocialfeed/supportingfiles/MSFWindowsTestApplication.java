/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.supportingfiles;

import org.mysocialfeed.services.MainServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mysocialfeed.models.DatabaseManager;
import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;

/**
 *
 * @author Vincent
 */
public class MSFWindowsTestApplication extends Application {
 
    public static Connection conn;
    public static Statement stmt;
    
    private Injector mainServiceInjector;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Guice implementation:
        this.mainServiceInjector = Guice.createInjector(new MainServiceModule());
        
        
        accessAndSetupSQLServer(true);
        
        ScreensController mainController = new ScreensController(this.mainServiceInjector);
        registerAllScreens(mainController);
        
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

                stmt.execute(DatabaseManager.CREATE_TABLE_USERS_SQL);
                stmt.execute(DatabaseManager.CREATE_TABLE_FACEBOOK_SQL);
                stmt.execute(DatabaseManager.CREATE_TABLE_TWITTER_SQL);
                stmt.execute(DatabaseManager.CREATE_TABLE_GOOGLEPLUS_SQL);
                stmt.execute(DatabaseManager.CREATE_TABLE_PINTEREST_SQL);
                stmt.execute(DatabaseManager.CREATE_TABLE_POSTS_SQL);

                stmt.close();
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
     private void registerAllScreens(ScreensController mainController) {
       
        FXMLGetResourcer myFXMLGetResourcer = new FXMLGetResourcer();
              
        mainController.registerScreen(FXMLGetResourcer.welcomeScreenID, myFXMLGetResourcer.getWelcomeScreenFile());
        mainController.registerScreen(FXMLGetResourcer.userMainScreenID, myFXMLGetResourcer.getUserMainScreenFile());
        mainController.registerScreen(FXMLGetResourcer.userSignUpScreenID, myFXMLGetResourcer.getUserSignUpScreenFile());
        mainController.registerScreen(FXMLGetResourcer.userAccountCreatedScreenID, myFXMLGetResourcer.getUserAccountCreatedScreenFile());
        
        mainController.registerScreen(FXMLGetResourcer.userAddAccountScreenID, myFXMLGetResourcer.getUserAddAccountScreenFile());
        mainController.registerScreen(FXMLGetResourcer.userFacebookScreenID, myFXMLGetResourcer.getUserFacebookScreenFile());
        mainController.registerScreen(FXMLGetResourcer.userGooglePlusScreenID, myFXMLGetResourcer.getUserGooglePlusScreenFile());
        mainController.registerScreen(FXMLGetResourcer.userPinterestScreenID, myFXMLGetResourcer.getUserPinterestScreenFile());
        mainController.registerScreen(FXMLGetResourcer.userTwitterScreenID, myFXMLGetResourcer.getUserTwitterScreenFile());
        
        mainController.registerScreen(FXMLGetResourcer.addFacebookAccountScreenID, myFXMLGetResourcer.getAddFacebookScreenAccountScreenFile());
        mainController.registerScreen(FXMLGetResourcer.addGooglePlusAccountScreenID, myFXMLGetResourcer.getAddGooglePlusAccountScreenFile());
        mainController.registerScreen(FXMLGetResourcer.addPinterestAccountScreenID, myFXMLGetResourcer.getAddPinterestAccountScreenFile());
        mainController.registerScreen(FXMLGetResourcer.addTwitterAccountScreenID, myFXMLGetResourcer.getAddTwitterAccountScreenFile());
        mainController.registerScreen(FXMLGetResourcer.AccountCreationResultScreenID, myFXMLGetResourcer.getAccountCreationResultScreenFile());
        
        mainController.setScreen(FXMLGetResourcer.welcomeScreenID); 
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}