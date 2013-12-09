package jsoncache;

import constants.Constants;

public class JSONException extends Exception {
    public JSONException() {
        super(Constants.ERROR_WITH_JSON);
    }
}
