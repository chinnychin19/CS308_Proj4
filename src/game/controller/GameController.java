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
    private int myModeIndex;
    private AbstractMode[] myModeArray;
    public static final int INDEX_WANDERING = 0, INDEX_WILD_BATTLE = 1;

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
        myModeArray[myModeIndex].paint(); // initially paint the state before waiting for key input
        myView.addKeyListener(myModeArray[myModeIndex]);
    }
    
    /**
     * Puts the Game in Wandering Mode
     */
    public void setWanderingMode() {
        setMode(INDEX_WANDERING);
    }
    
    /**
     * Puts the Game in WildBattleMode
     * @param monster - The Monster of the WildBattle
     */
    public void setWildBattleMode(Monster monster) {
        // need to set the monster before activating the mode so it can draw the image
        ((WildBattleMode) myModeArray[INDEX_WILD_BATTLE]).setEnemyMonster(monster);
        setMode(INDEX_WILD_BATTLE);
    }
    
    public AbstractMode getMode(){
        return myModeArray[myModeIndex];
    }
    /**
     * Loads the models state and then sets the default mode
     */
    public void loadState () {
        myModel.loadState();
        myModeArray[myModeIndex].paint(); // paints changes caused by loading the save state
    }

    private void setMode (int newModeIndex) {
        myModeArray[myModeIndex].turnOff();
        myModeIndex = newModeIndex;
        myModeArray[newModeIndex].turnOn();
    }

    private void initModes () {
        myModeArray = new AbstractMode[] {
                                          new WanderingMode(myModel, myView),
                                          new WildBattleMode(myModel, myView),
        };
        myModeIndex = INDEX_WANDERING;
    }

}
