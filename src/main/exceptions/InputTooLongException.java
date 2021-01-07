package exceptions;

// Exception for input that is too long
public class InputTooLongException extends Exception {
    public InputTooLongException(String msg) {
        super(msg);
    }
}
