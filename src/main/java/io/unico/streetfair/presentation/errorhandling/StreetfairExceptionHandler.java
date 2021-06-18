package io.unico.streetfair.presentation.errorhandling;

import io.unico.streetfair.domain.exception.FairConflictException;
import io.unico.streetfair.domain.exception.FairNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StreetfairExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        var response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Object>(response, new HttpHeaders(), response.getStatus());
    }

    @ExceptionHandler(FairConflictException.class)
    public final ResponseEntity<Object> handleFairConflictException(FairConflictException ex, WebRequest request) {
        var response = new ExceptionResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
    }

    @ExceptionHandler(FairNotFoundException.class)
    public final ResponseEntity<Object> handleFairNotFoundException(FairNotFoundException ex, WebRequest request) {
        var response = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var response = new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage());
        return new ResponseEntity<Object>(response, new HttpHeaders(), response.getStatus());
    }

}
