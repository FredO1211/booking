package com.github.fredO1211.booking.service.handlers;

import com.github.fredO1211.booking.service.exceptions.ElementDoesNotExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ElementDoesNotExistHandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementDoesNotExistException.class)
    public ResponseEntity<Object> handleWebException(RuntimeException e, WebRequest webRequest) {
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }
}
