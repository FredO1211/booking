package com.github.fredO1211.booking.service.dto;

import com.github.fredO1211.booking.domain.Booking;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
public class BookingDTO {
    @Future
    private LocalDate startOfBooking;
    @Future
    private LocalDate endOfBooking;
    private String description;
    @Positive
    private int countOfGuests;
    public Booking toBooking(BookingDTO dto){
        return new Booking();
    }
}
