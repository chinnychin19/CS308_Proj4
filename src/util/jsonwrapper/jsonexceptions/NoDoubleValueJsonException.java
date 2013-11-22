package util.jsonwrapper.jsonexceptions;

public class NoDoubleValueJsonException extends SmartJsonException {
    private static final String MESSAGE = "No Double value for the given key";
    public NoDoubleValueJsonException(){
        super(MESSAGE);
    }
}
