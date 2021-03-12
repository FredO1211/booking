package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.domain.Guest;
import java.util.List;

public interface GuestService extends CrudService<Guest, Long> {
    void delete(Long id);
    List<Guest> getAll();
}
