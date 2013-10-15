/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.screensframework;

/**
 *
 * @author Vincent
 */
public class NodeController {
    public Object urlOrNode;
    public ControlledScreen currentNodController;
    
    public NodeController(Object urlOrNode, ControlledScreen controller) {
        this.urlOrNode = urlOrNode;
        this.currentNodController = controller;
    }
}
