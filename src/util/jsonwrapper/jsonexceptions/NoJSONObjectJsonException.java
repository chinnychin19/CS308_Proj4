package util.jsonwrapper.jsonexceptions;

public class NoJSONObjectJsonException extends SmartJsonException {
    private static final String MESSAGE = "No JSON object value for the given key";
    public NoJSONObjectJsonException(){
        super(MESSAGE);
    }
}
