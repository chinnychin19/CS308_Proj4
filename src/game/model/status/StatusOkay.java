package game.model.status;

import constants.Constants;
import game.model.GameModel;

public class StatusOkay extends Status {

    public StatusOkay (GameModel model) {
        super(model);
    }

    @Override
    public String getName() {
        return Constants.STATUS_OKAY;
    }
}
