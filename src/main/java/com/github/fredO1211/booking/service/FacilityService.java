package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.domain.Facility;

public interface FacilityService extends CrudService<Facility, Long> {
    Facility update(Long id, Facility source);

    void delete(Long id);
}
