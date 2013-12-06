package game.controller;

import util.Sound;
import game.controller.state.option.AbstractMainOptionState;
import game.controller.state.option.TrainerMainOptionState;
import game.model.FightingNPC;
import game.model.GameModel;
import game.model.battle.Battle;
import game.model.battle.NPCParty;
import game.model.battle.WildMonsterParty;
import game.model.battle.PlayerParty;
import game.view.GameView;

public class TrainerBattleMode extends AbstractBattleMode {

    public TrainerBattleMode (GameModel model, GameView view, FightingNPC enemy) {
        super(model, view);
        PlayerParty attacker =
                new PlayerParty(getModel().getController(), getModel().getPlayer());
        NPCParty defender = new NPCParty(getModel().getController(), enemy);
        myBattle = new Battle(attacker, defender, this);
        attacker.setBattle(myBattle);
        defender.setBattle(myBattle);
        mySelectedOption = 0;
        mySelectedAttack = 0;
        mySound = new Sound("music/nfl.wav");
    }
    
    @Override
    public AbstractMainOptionState getAMainOptionState () {
        return new TrainerMainOptionState(this);
    }
}
