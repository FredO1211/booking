package com.github.fredO1211.booking.service.exceptions;


import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class UnavailableDateException extends IllegalArgumentException{
    public UnavailableDateException() {
        super(MessageProvider.UNAVAILABLE_DATE_EXCEPTION_MSG);
    }
}
