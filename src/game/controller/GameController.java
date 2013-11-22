package game.controller;

import javax.swing.JOptionPane;
import game.model.GameModel;
import game.view.GameView;


public class GameController {
    private GameView myView;
    private GameModel myModel;
    private int myModeIndex;
    private AbstractMode[] myModeArray;
    private static final int INDEX_WANDERING = 0;

    public GameController (String nameOfGame, GameView view) {
        myView = view;
        try {
            myModel = new GameModel(nameOfGame);
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
        myView.removeKeyListener(myModeArray[myModeIndex]);
        myModeIndex = newModeIndex;
        myView.addKeyListener(myModeArray[newModeIndex]);
    }

    private void initModes () {
        myModeArray = new AbstractMode[] {
                                          new WanderingMode(myModel, myView),
        };
        myModeIndex = INDEX_WANDERING;
    }

    public void loadState () {
        myModel.loadState();
        myModeArray[myModeIndex].paint(); // paints changes caused by loading the save state
    }
}
