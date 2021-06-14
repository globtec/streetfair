package io.unico.streetfair.domain.exception;

public class FairNotFoundException extends Exception {

    public FairNotFoundException(String message) {
        super(message);
    }

    public FairNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
