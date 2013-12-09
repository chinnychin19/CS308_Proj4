package util.jsonwrapper.jsonexceptions;

public class NoBooleanValueJsonException extends SmartJsonException {
    private static final String MESSAGE = "No Double value for the given key"; //TODO: revisit this package for constants

    public NoBooleanValueJsonException () {
        super(MESSAGE);
    }
}
