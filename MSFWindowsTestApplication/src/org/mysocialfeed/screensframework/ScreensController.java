/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import com.google.inject.Injector;
import java.net.URL;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 *
 * @author Vincent
 */
public class ScreensController extends StackPane {
    //Contains the screens to be displayed in a HasMap collection

    private final HashMap<String, Object> screens = new HashMap<>();
    private final Injector injector;
    
    public ScreensController(Injector injector) {
        super();
        this.injector = injector;
    }
    
    public void registerScreen(String name, URL url){
        screens.put(name, url);
    }
    
  
    
    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    private Node loadOrFindScreen(String name) {
        Object urlOrNode = screens.get(name);
        if (urlOrNode == null){
            return null;
        }
        if (urlOrNode instanceof Node){
            return (Node) urlOrNode;
        }
        try {
            FXMLLoader loader = new FXMLLoader((URL)urlOrNode);
            
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> paramClass) {
                    return injector.getInstance(paramClass);
                }
            });

            Parent loadScreen = (Parent)loader.load();
            ControlledScreen myScreenController = ((ControlledScreen) loader.getController());
            myScreenController.setScreenParent(this);
            screens.put(name, loadScreen);
            
            return loadScreen;
        } catch (Exception e) {
            System.err.println("Error while loading screen \"" + name + "\" : " + e.getMessage());
            return null;
        }
    }
    
    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is being added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    public boolean setScreen(final String name) {       
        Node screen = loadOrFindScreen(name);
        if (screen != null) {   //if screen(s) are loaded
            if (!getChildren().isEmpty()) {    //if there is more than one screen already displayed
                getChildren().remove(0);                    //remove the displayed screen
                getChildren().add(0, screen);     //add the screen
            }
            else { // if no screen displayed yet
                getChildren().add(screen);       
            }
            return true;
        } else {
            System.err.println("screen hasn't been registered. Screen name : " + screens.get(name) + "\n");
            return false;
        }
    }
}
