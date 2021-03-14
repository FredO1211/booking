package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.repository.FacilityRepository;
import com.github.fredO1211.booking.service.exception.EntityNotFoundException;
import com.github.fredO1211.booking.service.exception.UnavailableNameException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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
        // given
        Facility facility = new Facility("name", 30);
        when(repository.isFacilityExists(anyString())).thenReturn(true);
        // when + then
        assertThrows(UnavailableNameException.class, () -> service.valid(facility));
    }

    @Test
    void shouldThrowElementDoesNotExistExceptionWhenRepositoryReturnNull() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        // when + then
        assertThrows(EntityNotFoundException.class,
                () -> service.update(1L, new Facility("name", 211)));
    }

    @Test
    void shouldChangeFacilityNameAndBasicRentAmountWhenSourcePassValid() {
        // given
        Facility toUpdate = new Facility("name", 100);
        Facility source = new Facility("rename", 200);
        when(repository.isFacilityExists(anyString())).thenReturn(false);
        // when
        service.update(toUpdate, source);
        // then
        assertThat(toUpdate.getName().equals(source.getName())
                && toUpdate.getBasicRentAmount().equals(source.getBasicRentAmount())).isTrue();
    }
}