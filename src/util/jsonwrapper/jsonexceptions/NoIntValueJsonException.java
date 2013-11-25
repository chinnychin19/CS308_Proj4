package util.jsonwrapper.jsonexceptions;

public class NoIntValueJsonException extends SmartJsonException {
    private static final String MESSAGE = "No Int value for the given key";
    public NoIntValueJsonException(){
        super(MESSAGE);
    }
}
