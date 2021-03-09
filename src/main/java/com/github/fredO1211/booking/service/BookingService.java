package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.service.dto.BookingDTO;

public interface BookingService extends CrudService<Booking, Long>{
    void delete(Long id);
    Booking update(Long id, BookingDTO dto);
}
