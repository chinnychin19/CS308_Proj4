package game.controller;

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
    }

    @Override
    public AbstractMainOptionState getAMainOptionState () {
        return new WildMainOptionState(this);
    }
}
