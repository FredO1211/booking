package com.github.fredO1211.booking.service.dto;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.domain.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExtendsBookingDTOTest {

    @Test
    void shouldReturnCorrectBookingDtoObject(){
        // given
        var guest = mock(Guest.class);
        var payment = mock(Payment.class);
        var facility = mock(Facility.class);
        var booking = new Booking(guest, LocalDate.now(),LocalDate.now(),"desc",payment,facility);
        when(guest.getId()).thenReturn(1L);
        when(payment.getId()).thenReturn(1L);
        when(facility.getId()).thenReturn(1L);
        // when
        ExtendsBookingDTO bookingDTO = ExtendsBookingDTO.toDTO(booking);
        // then
        assertEquals(booking.getStartOfBooking(), bookingDTO.getStartOfBooking());
        assertEquals(booking.getEndOfBooking(), bookingDTO.getEndOfBooking());
        assertEquals(booking.getDescription(), bookingDTO.getDescription());
    }

}