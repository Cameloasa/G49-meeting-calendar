package se.lexicon.exceptions;

public class AuthenticationFieldsException extends Exception {


    public AuthenticationFieldsException(String message) {
        super(message);
    }

    public AuthenticationFieldsException(String message, Throwable cause) {
        super(message, cause);
    }
}
