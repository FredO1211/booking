package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.repository.GuestRepository;
import com.github.fredO1211.booking.service.GuestService;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

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
    public Guest update(Guest guest) {
        return null;
    }

    @Override
    public void delete(Guest guest) {

    }
}
