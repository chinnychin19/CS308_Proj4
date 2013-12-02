package game.controller.optionState;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.WildBattleMode;
import game.model.Item;
import game.model.Player;
import game.model.attack.Attack;

public class ItemOptionState extends AbstractOptionState {

    public ItemOptionState (AbstractBattleMode mode) {
        super(mode);
    }

    @Override
    public void act(Input input) {
        super.act(input);
        if(mySelected >= getItems().size()){
            mySelected = Math.max(0,getItems().size()-1);
        }
    }
    
    @Override
    public void paint () {
        super.paint();


        int x = 15;
        int y = 30;
        int inc = 50;
        
        List<Item> items = getItems();
        for (int i = 0; i < items.size(); i++) {
            if (i == mySelected) {
                myBuffer.setColor(Color.white);
            }
            myBuffer.drawString(items.get(i).getName(), x, y + i * inc);
            if (i == mySelected) {
                myBuffer.setColor(Color.black);
            }
        }
    }

    private List<Item> getItems () {
        Player player = myMode.getModel().getPlayer();
        return player.getItems();
    }

    @Override
    protected void onInteract () {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onBack () {
        myMode.setOptionState(new MainOptionState(myMode));
    }

}
