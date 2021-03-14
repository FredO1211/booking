package com.github.fredO1211.booking.service.exception;

import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class EntityNotFoundException extends IllegalArgumentException {
    public EntityNotFoundException() {
        super(MessageProvider.ID_DOES_NOT_EXIST_MSG);
    }
}
