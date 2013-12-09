package util.jsonwrapper.jsonexceptions;

public class NoBooleanValueJsonException extends SmartJsonException {
    private static final String MESSAGE = "No Double value for the given key";

    public NoBooleanValueJsonException () {
        super(MESSAGE);
    }
}
