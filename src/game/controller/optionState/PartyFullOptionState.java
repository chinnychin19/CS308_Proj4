package game.controller.optionState;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.BattleOverState;
import game.controller.state.option.MainOptionState;
import game.controller.state.option.TextOptionState;
import game.model.Monster;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import constants.Constants;


public class PartyFullOptionState extends AbstractOptionState {

    public PartyFullOptionState (AbstractBattleMode mode) {
        super(mode);
    }

    @Override
    public void paint () {

        List<Monster> monsters = getMonsters();

        myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                          myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        int x = 15;
        int y = 30;
        int inc = 50;
        for (int i = 0; i < monsters.size(); i++) {
            if (i == mySelected) {
                myBuffer.setColor(Color.white);
            }
            myBuffer.drawString(monsters.get(i).getName(), x, y + i * inc);
            if (i == mySelected) {
                myBuffer.setColor(Color.black);
            }
        }
    }

    @Override
    public void act (Input input) {
        super.act(input);
        if (mySelected >= getMonsters().size()) {
            mySelected = Math.max(0, getMonsters().size() - 1);
        }
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
