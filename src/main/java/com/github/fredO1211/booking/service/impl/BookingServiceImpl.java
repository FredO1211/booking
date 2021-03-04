package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.repository.BookingRepository;
import com.github.fredO1211.booking.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    public final BookingRepository repository;

    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Booking valid(Booking booking) {
        return null;
    }

    @Override
    public Booking save(Booking booking) {
        return null;
    }

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public void delete(Booking booking) {

    }
}
