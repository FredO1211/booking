package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.messageprovider.MessageProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingValidatorTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDiscountIsHigherThanStayCost() {
        // given
        Booking booking = new Booking(null, LocalDate.now(), LocalDate.now().plusDays(1), null, null, null);
        Payment payment = new Payment("code", 100, 200, 0, LocalDate.now().plusDays(1));
        // when + then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BookingValidator.validNewBooking(booking, payment));
        assertEquals(MessageProvider.BOOKING_DISCOUNT_HIGHER_THAN_STAY_COST_MSG, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenAdvancedIsHigherThanStayCost() {
        // given
        Booking booking = new Booking(null, LocalDate.now(), LocalDate.now().plusDays(1), null, null, null);
        Payment payment = new Payment("code", 100, 0, 200, LocalDate.now().plusDays(1));
        // when + then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BookingValidator.validNewBooking(booking, payment));
        assertEquals(MessageProvider.BOOKING_ADVANCE_HIGHER_THAN_STAY_COST_MSG, exception.getMessage());
    }

    @Test
    void shouldReturnCorrectValueOfStayCost() {
        // given
        Booking booking = new Booking(null, LocalDate.now(), LocalDate.now().plusDays(2), null, null, null);
        Payment payment = new Payment("code", 100, 20, 50, LocalDate.now().plusDays(1));
        // when + then
        assertEquals(180, BookingValidator.getStayCost(booking, payment));
    }

}