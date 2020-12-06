package myJournal.util;

public class UnauthenticatedSessionException extends RuntimeException {
    public UnauthenticatedSessionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public UnauthenticatedSessionException() {
        super("Session is not properly authenticated.");
    }
}
