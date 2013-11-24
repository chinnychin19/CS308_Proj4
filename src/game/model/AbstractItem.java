package game.model;

import util.jsonwrapper.SmartJsonObject;

/**
 * Contains the basic information for an item and extrends AbstractModelOjbject
 * @author tylernisonoff
 *
 */
public abstract class AbstractItem extends AbstractModelObject {

    public AbstractItem (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }
    
}
