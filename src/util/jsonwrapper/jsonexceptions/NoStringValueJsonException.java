package util.jsonwrapper.jsonexceptions;

public class NoStringValueJsonException extends SmartJsonException {
    private static final String MESSAGE = "No String value for the given key";
    public NoStringValueJsonException(){
        super(MESSAGE);
    }
}
