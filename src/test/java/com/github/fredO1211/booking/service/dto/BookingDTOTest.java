package com.github.fredO1211.booking.service.dto;

import com.github.fredO1211.booking.domain.Booking;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingDTOTest {

    @Test
    void shouldReturnCorrectBookingDtoObject(){
        // given
        var booking = new Booking(null, LocalDate.now(),LocalDate.now(),"desc",null,null);
        // when
        BookingDTO bookingDTO = BookingDTO.toDTO(booking);
        //then
        assertEquals(booking.getStartOfBooking(), bookingDTO.getStartOfBooking());
        assertEquals(booking.getEndOfBooking(), bookingDTO.getEndOfBooking());
        assertEquals(booking.getDescription(), bookingDTO.getDescription());
    }

}