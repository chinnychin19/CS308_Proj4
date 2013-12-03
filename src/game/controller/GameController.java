package game.controller;

import java.awt.Graphics;
import javax.swing.JOptionPane;
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
    public GameController (String nameOfGame, GameView view) {
        myView = view;
        try {
            myModel = new GameModel(nameOfGame, this);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading game data.");
            System.exit(1);
        }
        initModes();
        myCurrentMode.paint();
        myView.addKeyListener(myCurrentMode);
    }
    
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
