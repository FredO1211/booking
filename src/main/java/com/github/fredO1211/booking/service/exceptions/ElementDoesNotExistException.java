package com.github.fredO1211.booking.service.exceptions;

import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class ElementDoesNotExistException extends IllegalArgumentException {
    public ElementDoesNotExistException() {
        super(MessageProvider.ID_DOES_NOT_EXIST_MSG);
    }
}
