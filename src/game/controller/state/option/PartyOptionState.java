package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.WildBattleMode;
import game.model.Monster;
import game.model.attack.Attack;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

public class PartyOptionState extends AbstractOptionState{
    public PartyOptionState (AbstractBattleMode mode) {
        this(mode, true);
    }
    
    public PartyOptionState (AbstractBattleMode mode, boolean canGoBack){
        super(mode,"PARTY",canGoBack);

    }
    
    @Override
    public void paint () {
        // TODO Auto-generated method stub
        List<Monster> monsters = getMonsters();
        myNumOptions = monsters.size();

        myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                                 myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        // TODO : For Tyler - This is duplicated code - same as in MainOptionState.java

        int x = 15;
        int y = 30;
        int inc = 50;
        
        for (int i = 0; i < monsters.size(); i++) {
            
            if (i % 3 == 0 && i != 0) {
                x = x + 3 * inc;
                y = 30;
            }
            if (i == mySelected) myBuffer.setColor(Color.white); 
            
            myBuffer.drawString(monsters.get(i).getName(), x, y + i % 3 * inc);
            if (i == mySelected) {
                myBuffer.setColor(Color.black);
            }
        }
    }
    
    @Override
    public void act(Input input) {
        super.act(input);
        if(mySelected >= getMonsters().size()){
            mySelected = Math.max(0, getMonsters().size()-1);
        }
    }
    
    @Override
    public void onInteract(){
        List<Monster> monsters = getMonsters();
        Monster selectedMonster = monsters.get(mySelected);
        myMode.getBattle().getPlayerParty().setCurrentMonster(selectedMonster);
        myMode.setOptionState(new MainOptionState(myMode));
        myMode.getBattle().doNextTurn();
    }
    
    @Override
    protected void onBack () {
        if(canGoBack()){
            myMode.setOptionState(new MainOptionState(myMode));
        }
    }

    protected List<Monster> getMonsters(){
        return myMode.getBattle().getPlayerParty().getMonsters();
    }
}
