package com.github.fredO1211.booking.service.exceptions;

import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class UnavailableNameException extends IllegalArgumentException {
    public UnavailableNameException() {
        super(MessageProvider.UNAVAILABLE_NAME_EXCEPTION_MSG);
    }
}
