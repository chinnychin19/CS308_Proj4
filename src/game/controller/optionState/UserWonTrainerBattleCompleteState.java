package game.controller.optionState;

import game.controller.AbstractBattleMode;
import game.model.FightingNPC;
import game.model.Player;
import game.model.battle.Battle;

public class UserWonTrainerBattleCompleteState extends AbstractBattleCompleteState {

    public UserWonTrainerBattleCompleteState (AbstractBattleMode mode) {
        super(mode, "You won :)");
    }

    @Override
    protected void onInteract () {
        Battle battle = myMode.getBattle();
        FightingNPC enemy = ((FightingNPC) battle.getEnemyParty().getFighter());
        Player player = ((Player) battle.getPlayerParty().getFighter());
        enemy.setDefeated(true);
        enemy.giveKeyItemGifts(player);
        //TODO: gift dialogue
        
        //TODO: money
        //TODO: post dialogue
        myMode.getController().setWanderingMode();
    }
}
