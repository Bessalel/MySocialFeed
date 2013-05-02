/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.supportingfiles;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

// for SQL :
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.mysocialfeed.screensframework.ScreensController;
import org.mysocialfeed.models.BuildAndFillDatabase;

/**
 *
 * @author Vincent
 */
public class MSFWindowsTestApplication extends Application {
 
    private Connection conn;
    private Statement stmt;
    private DataSource ds;
    private ResultSet rslt;
    
    public static String welcomeScreenID = "WelcomeScreen";
    //public static String welcomeScreenFile = ScreensController.class.getResource("WelcomeScreen.fxml").getPath();
    public static String welcomeScreenFile = "WelcomeScreen.fxml";
    
    
    public static String userMainScreenID = "UserMainScreen";
    //public static String userMainScreenFile = ScreensController.class.getResource("UserMainScreen.fxml").getPath();
    public static String userMainScreenFile = "UserMainScreen.fxml";
    
    public static String userTwitterTabScreenID = "UserTwitterTabScreenID";
    //public static String userTwitterTabScreenFile = ScreensController.class.getResource("UserTwitterTabScreen.fxml").getPath();
    public static String userTwitterTabScreenFile = "UserTwitterTabScreen.fxml";
    
    @Override
    public void start(Stage primaryStage) {
        
      
        System.out.println("test");
        
       try {
           // this.ds = (DataSource) BuildAndFillDatabase.ctx.get("jdbc/MySocialFeed");
           ds = (DataSource) BuildAndFillDatabase.ctx.get("jdbc/MySocialFeed");
            conn = this.ds.getConnection("root", "");
        
            conn.setAutoCommit(false);
        
            stmt = conn.createStatement();
            stmt.execute(BuildAndFillDatabase.CREATE_TABLE_USERS_SQL);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ScreensController mainController = new ScreensController();
        mainController.loadScreen(MSFWindowsTestApplication.welcomeScreenID, MSFWindowsTestApplication.welcomeScreenFile);
        mainController.loadScreen(MSFWindowsTestApplication.userMainScreenID, MSFWindowsTestApplication.userMainScreenFile);
        mainController.loadScreen(MSFWindowsTestApplication.userTwitterTabScreenID, MSFWindowsTestApplication.userTwitterTabScreenFile);
     
        mainController.setScreen(welcomeScreenID);
        
        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);
        primaryStage.setHeight(350);
        primaryStage.setWidth(400);
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