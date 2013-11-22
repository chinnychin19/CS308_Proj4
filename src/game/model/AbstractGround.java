package game.model;

import util.jsonwrapper.SmartJsonObject;

public abstract class AbstractGround extends AbstractViewable {

    public AbstractGround (World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(world, definition, objInWorld);
    }

    @Override
    public boolean canStepOn() {
        return true;
    }
}
