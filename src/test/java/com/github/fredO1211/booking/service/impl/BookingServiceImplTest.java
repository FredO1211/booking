package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.repository.BookingRepository;
import com.github.fredO1211.booking.service.exception.UnavailableDateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingServiceImplTest {
    static BookingRepository repository;
    static BookingServiceImpl service;
    static EmailServiceImpl mailProvider;
    static GuestServiceImpl guestService;
    static PaymentServiceImpl paymentService;

    @BeforeAll
    static void setup() {
        repository = mock(BookingRepository.class);
        mailProvider = mock(EmailServiceImpl.class);
        guestService = mock(GuestServiceImpl.class);
        paymentService = mock(PaymentServiceImpl.class);
        service = new BookingServiceImpl(repository, guestService, paymentService, mailProvider);
    }

    @Test
    void shouldThrowUnavailableDateExceptionWhenDateIsNotAvailable() {
        // given
        Facility facility = mock(Facility.class);
        Booking booking = new Booking(null, LocalDate.now(),LocalDate.now().plusDays(1),null,null,facility);
        when(repository.isAvailable(anyString(),anyString(),anyLong())).thenReturn(false);
        // when + then
        assertThrows(UnavailableDateException.class, ()->service.valid(booking));
    }
}