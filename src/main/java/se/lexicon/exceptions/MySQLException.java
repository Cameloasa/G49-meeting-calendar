package se.lexicon.exceptions;



public class MySQLException extends RuntimeException {

    public MySQLException(String message) {
        super(message);
    }

    public MySQLException(String message, Throwable cause) {
        super(message, cause);
    }
}
