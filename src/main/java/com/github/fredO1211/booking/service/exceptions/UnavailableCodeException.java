package com.github.fredO1211.booking.service.exceptions;

import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class UnavailableCodeException extends IllegalArgumentException {
    public UnavailableCodeException() {
        super(MessageProvider.UNAVAILABLE_NAME_EXCEPTION_MSG);
    }
}
