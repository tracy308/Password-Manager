package exceptions;

// Exception for passcode that does not meet characteristics of a passcode
public class InvalidPassCodeException extends Exception {
    public InvalidPassCodeException(String msg) {
        super(msg);
    }
}

