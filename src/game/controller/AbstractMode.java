package game.controller;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
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
    private boolean myIsMovementAllowed;
    public AbstractMode (GameModel model, GameView view) {
        myModel = model;
        myView = view;
        myGraphics = view.getBuffer();
        myInput = new Input();
        myStates = new ConcurrentLinkedQueue<AbstractState>();
        myLastKeyPressTime = 0;
        myIsMovementAllowed = true;
    }
    
    public GameModel getModel () {
        return myModel;
    }
    
    //TODO: Consider refactoring to have access to controller
    public GameController getController() {
       return myModel.getController();
    }
    
    /**
     * Called by actAndPaint method
     */
    public abstract void paint ();
    
    /**
     * Called by actAndPaint method
     */
    public abstract void act (); 
    
    //super should be called in sub classes
    public void turnOff() {
        removeAllKeyListeners();
        getInput().resetAllInputs();
        stopMusic();
    }
    
    /**
     * Initializes the key listeners appropriately and removes existing key listeners.  Also starts music for the current
     * mode
     */
    public void turnOn() {
        removeAllKeyListeners();
        getInput().resetAllInputs();
        getView().addKeyListener(this);
        startMusic();
    }
    
    /**
     * Removes all key listeners
     */
    private void removeAllKeyListeners() {
        for (KeyListener k : getView().getKeyListeners()) {
            getView().removeKeyListener(k);
        }
        //TODO: should we call stop music
    }
    
    /**
     * Start the music for the current mode
     */
    protected void startMusic() {
        mySound.start();
    }
    
    /**
     * Stop the music for the current mode
     */
    protected void stopMusic() {
        mySound.stop();
    }

    /**
     * 
     * @return an input object
     */
    public Input getInput(){
    	return myInput;
    }

    /**
     * 
     * @return a view
     */
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
        if (x == getInteractKey()) {
            myInput.setInput(InputIndex.INTERACT, flag);
        }
        if (x == getMenuKey()) {
            myInput.setInput(InputIndex.MENU, flag);
        }
        if (x == getBackKey()) {
            myInput.setInput(InputIndex.BACK, flag);
        }
        if(isMovementAllowed()){
            updateMovementInputs(e, flag);
        }
    }
    
    /**
     * Update the player based on the movement keys entered by the user
     * @param e KeyEvent pressed on keyboard/controller
     * @param flag boolean value to set keyEvent as 
     */
    private void updateMovementInputs(KeyEvent e, boolean flag){
        int x = e.getKeyCode();
        if (x == getUpKey()) {
            myInput.setInput(InputIndex.UP, flag);
        }
        if (x == getLeftKey()) {
            myInput.setInput(InputIndex.LEFT, flag);
        }
        if (x == getDownKey()) {
            myInput.setInput(InputIndex.DOWN, flag);
        }
        if (x == getRightKey()) {
            myInput.setInput(InputIndex.RIGHT, flag);
        }
    }
    
    /**
     * Disable the direction keys
     */
    public void turnMovementOff(){
        myIsMovementAllowed = false;
        getInput().setMovementOff();
    }
    
    /**
     * Enable the direction keys
     */
    public void turnMovementOn(){
        myIsMovementAllowed = true;
    }
    
    /**
     * 
     * @return An integer code representing the up key
     */
    protected int getUpKey () {
        return KeyEvent.VK_UP;
    }

    /**
     * 
     * @return An integer code representing the down key
     */
    protected int getDownKey () {
        return KeyEvent.VK_DOWN;
    }
    
    /**
     * 
     * @return An integer code representing the Left key
     */
    protected int getLeftKey () {
        return KeyEvent.VK_LEFT;
    }

    /**
     * 
     * @return An integer code representing the right key
     */
    protected int getRightKey () {
        return KeyEvent.VK_RIGHT;
    }
    
    /**
     * 
     * @return An integer code representing the interact key
     */
    protected int getInteractKey(){
        return KeyEvent.VK_Z;
    }
    
    /**
     * 
     * @return An integer code representing the back key
     */
    protected int getBackKey(){
        return KeyEvent.VK_X;
    }
    
    /**
     * 
     * @return An integer code representing the menu key
     */
    protected int getMenuKey(){
        return KeyEvent.VK_SPACE;
    }

    /**
     * Put text on Wandering mode display
     * @param state Text state
     */
    public void addDynamicState (AbstractState state) {
        myStates.add(state);
    }

    /**
     * Clear all text on the screen
     */
    public void clearDynamicStates () {
        myStates = new ConcurrentLinkedQueue<AbstractState>();
    }

    /**
     * Paint the display view
     */
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
    
    /**
     * Check to see if movement is enabled for the current state
     * @return
     */
    private boolean isMovementAllowed(){
        return myIsMovementAllowed;
    }
}
