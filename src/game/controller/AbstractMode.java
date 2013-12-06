package game.controller;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import util.Sound;
import constants.Constants;
import game.controller.state.AbstractState;
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
    protected Sound mySound;
    private long myLastKeyPressTime;
	private Queue<AbstractState> myStates;

    public AbstractMode (GameModel model, GameView view) {
        myModel = model;
        myView = view;
        myGraphics = view.getBuffer();
        myInput = new Input();
        myStates = new ConcurrentLinkedQueue<AbstractState>();
        myLastKeyPressTime = 0;
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
        mySound.stop();
    }
    
    public void turnOn() {
        System.out.println("starting");
        mySound.start();
    }

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
        long timeSinceKeyPress = System.currentTimeMillis() - myLastKeyPressTime;
        if (timeSinceKeyPress < Constants.KEY_DELAY_MILLISECONDS) { return; }
        myLastKeyPressTime = System.currentTimeMillis();
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

    //TODO: Comment methods below
    
    public void addDynamicState(AbstractState state ) {
    	myStates.add(state);
	}
    
    public void removeDynamicState(AbstractState st) {
    	myStates.remove(st);
    }
    
    protected void paintDynamicStates() {
		for(AbstractState state : myStates){
			state.paint();
		}
	}
    
    protected void actDynamicStates() {
    	for(AbstractState state : myStates){
			state.act(getInput());
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
