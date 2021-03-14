package com.github.fredO1211.booking.service.exception;

import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class UnavailableCodeException extends IllegalArgumentException {
    public UnavailableCodeException() {
        super(MessageProvider.UNAVAILABLE_CODE_EXCEPTION_MSG);
    }
}
