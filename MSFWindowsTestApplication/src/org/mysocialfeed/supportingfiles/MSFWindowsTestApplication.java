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

// for SQL :

import org.mysocialfeed.screensframework.ScreensController;

/**
 *
 * @author Vincent
 */
public class MSFWindowsTestApplication extends Application {
 
    public static Connection conn;
    public static Statement stmt;
    
    public static final String welcomeScreenID = "WelcomeScreen";
    public static final String welcomeScreenFile = "WelcomeScreen.fxml";
    
    public static final String userMainScreenID = "UserMainScreen";
    public static final String userMainScreenFile = "UserMainScreen.fxml";
    
    public static final String userTwitterTabScreenID = "UserTwitterTabScreenID";
    public static final String userTwitterTabScreenFile = "UserTwitterTabScreen.fxml";
    
    public static final String userSignUpScreenID = "UserSignUpScreen";
    public static final String userSignUpScreenFile = "UserSignUpScreen.fxml";
    
    public static final String userAccountCreatedScreenID = "UserAccountCreatedScreen";
    public static final String userAccountCreatedScreenFile = "UserAccountCreatedScreen.fxml";
    
    public static void accessSQL(){
        
        try {  
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mysocialfeed","root","");
            conn.setAutoCommit(false);
 
            stmt = conn.createStatement();
            stmt.execute(BuildAndFillDatabase.CREATE_TABLE_USERS_SQL);
            stmt.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        accessSQL();
        
        ScreensController mainController = new ScreensController();
        mainController.loadScreen(MSFWindowsTestApplication.welcomeScreenID, MSFWindowsTestApplication.welcomeScreenFile);
        mainController.loadScreen(MSFWindowsTestApplication.userMainScreenID, MSFWindowsTestApplication.userMainScreenFile);
        mainController.loadScreen(MSFWindowsTestApplication.userTwitterTabScreenID, MSFWindowsTestApplication.userTwitterTabScreenFile);
        mainController.loadScreen(MSFWindowsTestApplication.userSignUpScreenID, MSFWindowsTestApplication.userSignUpScreenFile);
        mainController.loadScreen(MSFWindowsTestApplication.userAccountCreatedScreenID, MSFWindowsTestApplication.userAccountCreatedScreenFile);
        mainController.setScreen(welcomeScreenID);
        
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