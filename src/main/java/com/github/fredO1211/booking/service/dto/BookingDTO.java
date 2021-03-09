package com.github.fredO1211.booking.service.dto;

import com.github.fredO1211.booking.domain.Booking;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class BookingDTO {
    @Future
    private LocalDate startOfBooking;
    @Future
    private LocalDate endOfBooking;
    private String description;
    @Positive
    private int countOfGuests;

    public LocalDate getStartOfBooking() {
        return startOfBooking;
    }

    public LocalDate getEndOfBooking() {
        return endOfBooking;
    }

    public String getDescription() {
        return description;
    }

    public int getCountOfGuests() {
        return countOfGuests;
    }
    public Booking toBooking(BookingDTO dto){
        return new Booking();
    }
}
