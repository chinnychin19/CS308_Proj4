package game.controller.state.mainmenu;

import java.util.List;
import constants.Constants;
import game.controller.MainMenuMode;
import game.controller.state.NotListableException;
import game.model.KeyItem;

public class KeyItemMenuState extends AbstractListableState {

    public KeyItemMenuState (MainMenuMode mode) {
        super(Constants.MAIN_MENU_KEY_ITEM, mode);
    }

    @Override
    public void paint(){
        super.paint();
        
        try {
            paintList(getKeyItemList());
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onInteract () {

    }
    
    private List<KeyItem> getKeyItemList () {
        return getMode().getModel().getPlayer().getKeyItems();
    }

}
