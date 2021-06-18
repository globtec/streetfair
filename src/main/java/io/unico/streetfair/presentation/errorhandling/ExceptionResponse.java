package io.unico.streetfair.presentation.errorhandling;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private final int status;

    private final String error;

    public ExceptionResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    public ExceptionResponse(HttpStatus httpStatus, String error) {
        this.status = httpStatus.value();
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
