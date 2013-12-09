package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.state.option.AbstractMainOptionState;
import game.controller.state.option.TextOptionState;


public class CatchWildBattleCompleteState extends AbstractWildBattleCompleteState {

    public CatchWildBattleCompleteState (AbstractBattleMode mode) {
        super(mode, Constants.MODE_MONSTER_CAUGHT);
    }

    @Override
    protected void onInteract () {
        if (myMode.getBattle().getPlayerParty().getMonsters().size() > Constants.MAX_PARTY_SIZE) {
            myMode.setOptionState(new TextOptionState(myMode, Constants.MODE_PARTY_FULL,
                                                      new PartyFullOptionState(myMode)));
        }
        else super.onInteract();
    }

}
