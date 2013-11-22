package util.jsonwrapper.jsonexceptions;

public class NoJSONArrayJsonException extends SmartJsonException {
    private static final String MESSAGE = "No JSON Array value for the given key";
    public NoJSONArrayJsonException(){
        super(MESSAGE);
    }
}
