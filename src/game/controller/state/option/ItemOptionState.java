package game.controller.state.option;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.WildBattleMode;
import game.controller.state.NotListableException;
import game.model.Item;
import game.model.Player;
import game.model.attack.Attack;

public class ItemOptionState extends AbstractListableOptionState {

    public ItemOptionState (AbstractBattleMode mode) {
        super(mode, "ITEMS");
    }

    @Override
    public void act(Input input) {
        super.act(input);
        actList(input, getItems());
    }
    
    @Override
    public void paint () {
        try { 
            paintList(getItems()); 
        }
        catch (NotListableException e) {
            e.printStackTrace();
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
        myMode.setOptionState(myMode.getAMainOptionState());
    }

}
