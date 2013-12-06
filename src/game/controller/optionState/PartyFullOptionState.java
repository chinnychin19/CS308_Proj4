package game.controller.optionState;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.state.NotListableException;
import game.controller.state.option.AbstractListableOptionState;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.BattleOverState;
import game.controller.state.option.AbstractMainOptionState;
import game.controller.state.option.TextOptionState;
import game.model.Monster;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import constants.Constants;


public class PartyFullOptionState extends AbstractListableOptionState {

    public PartyFullOptionState (AbstractBattleMode mode) {
        super(mode);
    }

    @Override
    public void paint () {
        try { 
            paintList(getMonsters()); 
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void act (Input input) {
        super.act(input);
        actList(input, getMonsters());
    }

    @Override
    public void onInteract () {
        List<Monster> monsters = getMonsters();
        Monster selectedMonster = monsters.get(mySelected);
        monsters.remove(selectedMonster);
        String goodbyeString = Constants.MODE_MONSTER_RELEASE_1 + selectedMonster.getName() + Constants.MODE_MONSTER_RELEASE_2;
        myMode.setOptionState(new BattleOverState(myMode, goodbyeString));
    }

    @Override
    protected void onBack () {
    }

    protected List<Monster> getMonsters () {
        return myMode.getBattle().getPlayerParty().getMonsters();
    }
}
