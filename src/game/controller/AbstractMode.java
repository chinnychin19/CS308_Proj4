package game.controller;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import constants.Constants;
import game.model.GameModel;
import game.view.GameView;


/**
 * This is an abstract class to represent the different modes of a game, i.e. Wandering, Battle,
 * etc. Each mode performs different with different user input and paints the screen differently.
 * This mode serves as a controller and has access to the view and the model.
 * 
 * @author Chinmay, rtoussaint
 * 
 */

public abstract class AbstractMode extends KeyAdapter {
    private GameModel myModel;
    private GameView myView;
    private Graphics myGraphics;
    private Input myInput;

    public AbstractMode (GameModel model, GameView view) {
        myModel = model;
        myView = view;
        myGraphics = view.getBuffer();
        myInput = new Input();
    }
    
    public GameModel getModel () {
        return myModel;
    }
    
    //TODO: Consider refactoring to have access to controller
    public GameController getController() {
       return myModel.getController();
    }
    
    public abstract void paint ();

    public abstract void act (); // TODO: take some sort of input object as parameter? or is this
                                 // the input object?
    
    //super should be called in sub classes
    public void turnOff() {
        getInput().resetAllInputs();
    }
    
    public abstract void turnOn();

    public Input getInput(){
    	return myInput;
    }

    protected GameView getView () {
        return myView;
    }
    
    /**
     * Returns the Graphics object to be used by subclasses when painting
     * 
     * @return
     */
    public Graphics getGraphics () {
        return myGraphics;
    }

    /**
     * Updates the state of the inputs and performs actions/repaints the screen.
     */
    @Override
    public void keyPressed (KeyEvent e) {
        updateInputs(e, true);
        actAndPaint();
    }

    /**
     * Updates the state of the inputs and performs actions/repaints the screen.
     */
    @Override
    public void keyReleased (KeyEvent e) {
        updateInputs(e, false);
        actAndPaint();
    }

    /**
     * Performs all actions resulting from input and repaints the screen.
     */
    private void actAndPaint () {
        act();
        paint(); // order matters. paint after action completed.
        myView.repaint();
    }

    /**
     * Updates the appropriate input boolean given a KeyEvent
     * 
     * @param e An event representing a key press or key release
     * @param flag Should be true if a key press, false if a key release
     */
    private void updateInputs (KeyEvent e, boolean flag) {
        int x = e.getKeyCode();
       if (x == KeyEvent.VK_UP) {
    	   myInput.setInput(InputIndex.UP, flag);
        }
        if (x == KeyEvent.VK_LEFT) {
        	myInput.setInput(InputIndex.LEFT, flag);
        }
        if (x == KeyEvent.VK_DOWN) {
        	myInput.setInput(InputIndex.DOWN, flag);
        }
        if (x == KeyEvent.VK_RIGHT) {
        	myInput.setInput(InputIndex.RIGHT, flag);
        }
        if (x == KeyEvent.VK_Z) {
        	myInput.setInput(InputIndex.INTERACT, flag);
        }
        if (x == KeyEvent.VK_SPACE) {
        	myInput.setInput(InputIndex.MENU, flag);
        }
        if (x == KeyEvent.VK_B) {
            myInput.setInput(InputIndex.BACK, flag);
    }
    }

    /**
     * Paints a border around the edge of the screen.
     */
    protected void paintBorder () {
        myGraphics.setColor(Constants.BORDER_COLOR);
        myGraphics.fillRect(0, 0, Constants.WIDTH, Constants.BORDER_THICKNESS);
        myGraphics.fillRect(0, Constants.HEIGHT - Constants.BORDER_THICKNESS,
                            Constants.WIDTH, Constants.HEIGHT);
        myGraphics.fillRect(0, Constants.BORDER_THICKNESS, Constants.BORDER_THICKNESS,
                            Constants.HEIGHT);
        myGraphics.fillRect(Constants.WIDTH - Constants.BORDER_THICKNESS, 0,
                            Constants.WIDTH, Constants.HEIGHT);
    }
}
