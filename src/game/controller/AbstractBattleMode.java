package game.controller;

import game.model.GameModel;
import game.view.GameView;

public abstract class AbstractBattleMode extends AbstractMode {

    public AbstractBattleMode (GameModel model, GameView view) {
        super(model, view);
    }

}
