/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.supportingfiles;

import org.mysocialfeed.services.MainServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.mysocialfeed.screensframework.FXMLGetResourcer;
import org.mysocialfeed.screensframework.ScreensController;

/**
 *
 * @author Vincent
 */
public class MSFWindowsTestApplication extends Application {
    
    private Injector mainServiceInjector;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Guice implementation:
        this.mainServiceInjector = Guice.createInjector(new MainServiceModule());
        
        ScreensController mainController = new ScreensController(this.mainServiceInjector);
        registerAllScreens(mainController);

        Scene scene = new Scene(mainController);
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(785.5);
        primaryStage.setMinWidth(1321);
       // primaryStage.setMaxHeight(958);
       // primaryStage.setMaxWidth(1281);
        
        primaryStage.show();
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