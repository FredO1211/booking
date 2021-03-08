package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.repository.BookingRepository;
import com.github.fredO1211.booking.service.BookingService;
import com.github.fredO1211.booking.service.MailProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    public final BookingRepository repository;
    public final MailProvider provider;

    public BookingServiceImpl(BookingRepository repository, EmailProvider provider) {
        this.repository = repository;
        this.provider = provider;
    }

    @Override
    public Booking save(Booking booking) {
        return null;
    }

    @Override
    public List<Booking> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Booking> getById(Long id) {
        return Optional.empty();
    }

    public Booking update(Booking toUpdate, Booking source) {
        return null;
    }

    @Override
    public Booking update(Long id, Booking source) {
        return null;
    }

    @Override
    public void delete(Booking booking) {

    }
}
