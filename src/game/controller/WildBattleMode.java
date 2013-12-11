package game.controller;

import constants.Constants;
import util.Sound;
import game.controller.optionState.AbstractBattleCompleteState;
import game.controller.optionState.UserLostWildBattleCompleteState;
import game.controller.optionState.UserWonWildBattleCompleteState;
import game.controller.state.option.AbstractMainOptionState;
import game.controller.state.option.WildMainOptionState;
import game.model.GameModel;
import game.model.Monster;
import game.model.battle.Battle;
import game.model.battle.WildMonsterParty;
import game.model.battle.PlayerParty;
import game.view.GameView;


/**
 * Represents WildBattleMode
 * 
 * @author tylernisonoff
 * 
 */
public class WildBattleMode extends AbstractBattleMode {

    public WildBattleMode (GameModel model, GameView view, Monster monster) {
        super(model, view);
        PlayerParty attacker =
                new PlayerParty(getModel().getController(), getModel().getPlayer());
        WildMonsterParty defender = new WildMonsterParty(getModel().getController(), monster);
        myBattle = new Battle(attacker, defender, this);
        attacker.setBattle(myBattle);
        defender.setBattle(myBattle);
        mySelectedOption = 0;
        mySelectedAttack = 0;
        mySound = new Sound(Constants.GAME_MUSIC, getController());
    }

    @Override
    public AbstractMainOptionState getAMainOptionState () {
        return new WildMainOptionState(this);
    }

    @Override
    public AbstractBattleCompleteState getBattleCompleteState (boolean didWin) {
        return didWin ? new UserWonWildBattleCompleteState(this) : 
            new UserLostWildBattleCompleteState(this);
    }
}
