package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;
import constants.Constants;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.MainOptionState;
import game.model.GameModel;
import game.model.Monster;
import game.model.Player;
import game.model.attack.Attack;
import game.model.battle.Battle;
import game.model.battle.WildMonsterParty;
import game.model.battle.WildPlayerParty;
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
        WildPlayerParty attacker =
                new WildPlayerParty(getModel().getController(), getModel().getPlayer());
        WildMonsterParty defender = new WildMonsterParty(getModel().getController(), monster);
        myBattle = new Battle(attacker, defender, this);
        attacker.setBattle(myBattle);
        defender.setBattle(myBattle);
        mySelectedOption = 0;
        mySelectedAttack = 0;
    }
}
