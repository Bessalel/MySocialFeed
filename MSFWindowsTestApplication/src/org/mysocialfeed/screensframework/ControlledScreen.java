/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

/**
 *
 * @author Vincent
 */
public class ControlledScreen {
    private ScreensController screenParent;
    
    //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController s) {
        this.screenParent = s;
    }
    
    protected ScreensController getScreenController() {
        return this.screenParent;
    }
    
    protected void onActivated() {}
    
    protected void onDeActivated() {}
}
