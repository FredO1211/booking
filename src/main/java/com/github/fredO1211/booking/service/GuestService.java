package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.domain.Guest;

public interface GuestService extends CrudService<Guest, Long>{
    Guest update(Long id, Guest source);

    public void delete(Long id);
}
