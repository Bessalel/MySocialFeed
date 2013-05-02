/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Vincent
 */
public class ScreensController extends StackPane {
    //Contains the screens to be displayed in a HasMap collection

    private HashMap<String, Node> screens = new HashMap<>();
    
    public ScreensController() {
        super();
    }
    
     //This method adds a given screen in the HasMap
    public void addScreen(String name, Node screen) {  
        screens.put(name, screen); 
    }
    
    //This method returns the screen filename from the screen given name
    public Node getScreen(String name) {
        return screens.get(name); 
    }
    
    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.err.println("Error while loading screen : " + e.getMessage());
            return false;
        }
    }
    
    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is being added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    public boolean setScreen(final String name) {       
        if (screens.get(name) != null) {   //if screen(s) are loaded
            if (!getChildren().isEmpty()) {    //if there is more than one screen already displayed
                getChildren().remove(0);                    //remove the displayed screen
                getChildren().add(0, screens.get(name));     //add the screen
            }
            else { // if no screen dusplayed yet
                getChildren().add(screens.get(name));       
            }
            return true;
        } else {
            System.err.println("screen hasn't been loaded. Screen name : " + screens.get(name) + "\n");
            return false;
        }
    }

    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.err.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}
