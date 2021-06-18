package io.unico.streetfair.domain.exception;

public class FairConflictException extends Exception {

    public FairConflictException(String message) {
        super(message);
    }

    public FairConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}
