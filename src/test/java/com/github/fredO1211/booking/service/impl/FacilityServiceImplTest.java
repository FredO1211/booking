package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.repository.FacilityRepository;
import com.github.fredO1211.booking.service.exceptions.UnavailableNameException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FacilityServiceImplTest {
    static FacilityRepository repository;
    static FacilityServiceImpl service;

    @BeforeAll
    static void setup() {
        repository = mock(FacilityRepository.class);
        service = new FacilityServiceImpl(repository);
    }

    @Test
    void shouldThrowUnavailableNameExceptionWhenNameIsExist() {
        //given
        Facility facility = new Facility("name", 30);
        when(repository.isFacilityExists(anyString())).thenReturn(true);
        // when + then
        assertThrows(UnavailableNameException.class, () -> service.valid(facility));
    }
}