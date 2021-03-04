package com.github.fredO1211.booking.service.dto;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.service.DateProvider;

import java.time.LocalDate;
import java.util.List;

public class SimplifiedBookingDTO {
    int bookingId;
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

    private SimplifiedBookingDTO(int bookingId, LocalDate startingDate, LocalDate endingDate) {
        this.bookingId = bookingId;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public List<LocalDate> getOtherDatesTaken() {
        return otherDatesTaken;
    }
}
