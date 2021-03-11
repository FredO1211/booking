package com.github.fredO1211.booking.service.handlers;

import com.github.fredO1211.booking.service.exceptions.IncorrectInputDataException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class IncorrectVerificationHandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncorrectInputDataException.class)
    public ResponseEntity<Object> handleWebException(RuntimeException e, WebRequest webRequest) {
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, webRequest);
    }
}
