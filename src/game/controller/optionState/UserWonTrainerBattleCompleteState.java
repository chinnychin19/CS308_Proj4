package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.model.FightingNPC;
import game.model.Player;
import game.model.battle.Battle;

public class UserWonTrainerBattleCompleteState extends AbstractBattleCompleteState {

    public UserWonTrainerBattleCompleteState (AbstractBattleMode mode) {
        super(mode, Constants.YOU_WON+ ((numKeyItemsToGive(mode)!=0) ? Constants.YOU_RECEIVED +numKeyItemsToGive(mode)+ Constants.DIALOGUE_KEY_ITEMS : Constants.BLANK_STRING));
    }

    @Override
    protected void onInteract () {
        Battle battle = myMode.getBattle();
        FightingNPC enemy = ((FightingNPC) battle.getEnemyParty().getFighter());
        Player player = ((Player) battle.getPlayerParty().getFighter());
        enemy.setDefeated(true);
        enemy.giveKeyItemGifts(player);
        //TODO: gift dialogue -- still needed???
        
        //TODO: post dialogue --- still needed???
        myMode.getController().setWanderingMode();
    }
    
    private static int numKeyItemsToGive(AbstractBattleMode mode){
        return ((FightingNPC) mode.getBattle().getEnemyParty().getFighter()).getKeyItems().size();
    }
}
