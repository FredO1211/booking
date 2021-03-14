package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.repository.GuestRepository;
import com.github.fredO1211.booking.service.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.fredO1211.booking.service.impl.GuestServiceImplAssert.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GuestServiceImplTest {
    static GuestRepository repository;
    static GuestServiceImpl service;

    @BeforeAll
    static void setup() {
        repository = mock(GuestRepository.class);
        service = new GuestServiceImpl(repository);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenEntityWithCurrentIdDoesNotExist() {
        // given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Guest guest = new Guest("name", "mail@mail.com", "45454554", null);
        // when + then
        assertThrows(EntityNotFoundException.class, () -> service.update(1L, guest));
    }

    @Test
    void shouldChangeDataWhenCodesAreTheSame() {
        Guest toUpdate = new Guest("name", "mail@mail.com", "45454554", null);
        Guest source = new Guest("new name", "new@mail.com", "11111111", "inf");
        // when
        service.update(toUpdate, source);
        // then
        then().guestObjectHasChangedWholeData(toUpdate, source);
    }
}