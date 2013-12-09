package game.controller;

import java.awt.Graphics;
import javax.swing.JOptionPane;

import constants.Constants;
import game.model.GameModel;
import game.model.Monster;
import game.view.GameView;

/**
 * The main controller for the Game
 * Orchestrates communication between the Game Model and the Game view
 * Also Orchestrates switching between modes
 * @author tylernisonoff
 *
 */
public class GameController {
    private GameView myView;
    private GameModel myModel;
    private AbstractMode myCurrentMode;
    private WanderingMode myMainMode;
    private boolean myIsVolumeOn;
    public GameController (String nameOfGame, GameView view) {
        myView = view;
        try {
            myModel = new GameModel(nameOfGame, this);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, Constants.ERROR_LOADING_GAME);
            System.exit(1);
        }
        initModes();
        myCurrentMode.paint();
        myView.addKeyListener(myCurrentMode);
        myIsVolumeOn = false;
        myCurrentMode.mySound.start();
    }
    
    /**
     * Turn music on if it is off.  Turn music off if it is on.
     */
    public void toggleMusic() {
        myIsVolumeOn = !myIsVolumeOn;
        if (myIsVolumeOn) {
            getMode().mySound.start();
        } else {
            getMode().mySound.stop();
        }
    }
    
    /**
     * 
     * @return boolean value for music currently playing
     */
    public boolean isMusicOn() {
        return myIsVolumeOn;
    }
    
    /**
     * 
     * @return model for the current game
     */
    public GameModel getModel() {
        return myModel;
    }
    
   
    public GameView getView(){
        return myView;
    }
    /**
     * Puts the Game in Wandering Mode
     */
    public void setWanderingMode() {
        setMode(myMainMode);
    }
    
    public AbstractMode getMode(){
        return myCurrentMode;
    }
    /**
     * Loads the models state and then sets the default mode
     */
    public void loadState () {
        myModel.loadState();
        myCurrentMode.paint(); // paints changes caused by loading the save state
    }

    /**
     * Set the mode based on the input argument
     * @param m mode to be set
     */
    public void setMode (AbstractMode m) {
        myCurrentMode.turnOff();
        myCurrentMode = m;
        myCurrentMode.turnOn();
    }

    private void initModes () {
        myMainMode = new WanderingMode(myModel, myView);
        myCurrentMode = myMainMode;
    }

}
