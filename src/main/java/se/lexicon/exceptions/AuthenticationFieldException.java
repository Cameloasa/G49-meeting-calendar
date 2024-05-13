package se.lexicon.exceptions;

public class AuthenticationFieldException extends Exception {


    public AuthenticationFieldException(String message) {
        super(message);
    }

    public AuthenticationFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
