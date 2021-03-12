package com.github.fredO1211.booking.service.exceptions;

import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class NoMailsFoundException extends IllegalArgumentException {
    public NoMailsFoundException() {
        super(MessageProvider.NO_MAILS_FOUND_MSG);
    }
}
