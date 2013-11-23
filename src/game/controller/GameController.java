package game.controller;

import javax.swing.JOptionPane;
import game.model.GameModel;
import game.view.GameView;


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

    public void setMode (int newModeIndex) {
        //TODO: we should "turn off" previous mode and "turn on" new mode
//        myView.removeKeyListener(myModeArray[myModeIndex]);
        myModeArray[myModeIndex].turnOff();
        myModeIndex = newModeIndex;
        myModeArray[newModeIndex].turnOn();
//        myView.addKeyListener(myModeArray[newModeIndex]);
    }

    private void initModes () {
        myModeArray = new AbstractMode[] {
                                          new WanderingMode(myModel, myView),
                                          new WildBattleMode(myModel, myView, null),
        };
        myModeIndex = INDEX_WANDERING;
    }

    public void loadState () {
        myModel.loadState();
        myModeArray[myModeIndex].paint(); // paints changes caused by loading the save state
    }
}
