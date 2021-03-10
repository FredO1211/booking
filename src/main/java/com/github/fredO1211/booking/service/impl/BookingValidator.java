package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.messageprovider.MessageProvider;

public class BookingValidator {
    public static void validNewBooking(Booking booking, Payment payment){
        int stayCost = booking.getStayLength() * payment.getCostPerNight() - payment.getDiscount();
        if (payment.getDiscount() > stayCost) {
            throw new IllegalArgumentException(MessageProvider.BOOKING_DISCOUNT_HIGHER_THAN_STAY_COST_MSG);
        }
        if (payment.getAdvanceSize() > stayCost) {
            throw new IllegalArgumentException(MessageProvider.BOOKING_ADVANCE_HIGHER_THAN_STAY_COST_MSG);
        }
    }
}
