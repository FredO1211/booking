package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.service.dto.BookingDTO;
import com.github.fredO1211.booking.service.dto.SimplifiedBookingDTO;
import org.springframework.hateoas.CollectionModel;

import java.time.YearMonth;

public interface BookingService extends CrudService<Booking, Long> {
    void delete(Long id);

    Booking update(Long id, BookingDTO dto);

    CollectionModel<SimplifiedBookingDTO> getSimplifiedBookingDTOList(YearMonth month, Long facilityId);

}
