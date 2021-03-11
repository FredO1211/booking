package com.github.fredO1211.booking.service.dto;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.service.DateProvider;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class SimplifiedBookingDTO {
    Long bookingId;
    LocalDate startingDate;
    LocalDate endingDate;
    List<LocalDate> otherDatesTaken;

    public SimplifiedBookingDTO(Booking booking) {
        this(booking.getId(),
                booking.getStartOfBooking(),
                booking.getEndOfBooking());

        this.otherDatesTaken = DateProvider.getDatesBetweenOthers(
                booking.getStartOfBooking(),
                booking.getEndOfBooking());
    }

    private SimplifiedBookingDTO(Long bookingId, LocalDate startingDate, LocalDate endingDate) {
        this.bookingId = bookingId;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
}
