package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.repository.GuestRepository;
import com.github.fredO1211.booking.service.GuestService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest valid(Guest guest) {
        return null;
    }

    @Override
    public Guest save(Guest guest) {
        return null;
    }

    @Override
    public List<Guest> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Guest> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Guest update(Guest toUpdate, Guest source) {
        return null;
    }

    @Override
    public void delete(Guest guest) {

    }
}
