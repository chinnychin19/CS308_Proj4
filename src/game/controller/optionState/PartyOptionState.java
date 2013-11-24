package game.controller.optionState;

import game.controller.Input;
import game.controller.WildBattleMode;
import game.model.Monster;
import game.model.attack.Attack;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

public class PartyOptionState extends AbstractOptionState{
    
    public PartyOptionState (WildBattleMode mode) {
        super(mode);
    }

    @Override
    public void paint () {
        // TODO Auto-generated method stub
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
    public void onInteract(){
        List<Monster> monsters = getMonsters();
        Monster selectedMonster = monsters.get(mySelected);
        myMode.getBattle().getPlayerParty().setCurrentMonster(selectedMonster);
        myMode.setOptionState(new MainOptionState(myMode));
        myMode.getBattle().registerUserCompleted();
    }
    
    @Override
    protected void onBack () {
        myMode.setOptionState(new MainOptionState(myMode));
    }

    private List<Monster> getMonsters(){
        return myMode.getBattle().getPlayerParty().getMonsters();
    }
}
