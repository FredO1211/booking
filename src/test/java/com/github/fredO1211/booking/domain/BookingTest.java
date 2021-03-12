package com.github.fredO1211.booking.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingTest {
    @Test
    void shouldReturnCorrectValueOfStayLength() {
        // given
        Booking booking = new Booking(null, LocalDate.now(), LocalDate.now().plusDays(2), null, null, null);
        // when + then
        assertEquals(2, booking.getStayLength());
    }
}