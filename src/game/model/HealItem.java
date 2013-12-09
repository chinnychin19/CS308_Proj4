package game.model;

import game.controller.AbstractMode;
import game.controller.state.TextState;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;


public class HealItem extends AbstractViewableObject {

    private Image myImage;

    public HealItem (GameModel model, World world, SmartJsonObject definition,
                     SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);

        myImage = new ImageIcon(Constants.HEAL_ITEM_IMAGE).getImage();
    }

    @Override
    public Image getImage () {
        return myImage;
        // TODO: consider moving this to abstract
    }

    @Override
    protected void onInteract () {
        for (Monster monster : getModel().getPlayer().getParty()) {
            monster.heal();
        }
        AbstractMode mode = getModel().getController().getMode();
        mode.addDynamicState(new TextState(mode,
                                           Constants.BORDER_THICKNESS,
                                           Constants.HEIGHT - Constants.BORDER_THICKNESS -
                                                   Constants.DIALOGUE_HEIGHT,
                                           Constants.WIDTH - 2 * Constants.BORDER_THICKNESS,
                                           Constants.DIALOGUE_HEIGHT,
                                           Constants.PROMPT_MONSTERS_HEALED));
    }

    @Override
    protected void onBack () {
        // TODO Auto-generated method stub

    }
}
