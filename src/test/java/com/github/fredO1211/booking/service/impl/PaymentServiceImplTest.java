package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.repository.PaymentRepository;
import com.github.fredO1211.booking.service.exception.UnavailableCodeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static com.github.fredO1211.booking.service.impl.PaymentServiceImplAssert.then;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {
    static PaymentRepository repository;
    static PaymentServiceImpl service;

    @BeforeAll
    static void setup() {
        repository = mock(PaymentRepository.class);
        service = new PaymentServiceImpl(repository);
    }

    @Test
    void shouldThrowUnavailableCodeExceptionWhenCodeIsNotAvailable() {
        // given
        when(repository.isCodeAvailable(anyString())).thenReturn(false);
        Payment payment = new Payment("code", 100, 0, 0, LocalDate.now().plusDays(1));
        // when + then
        assertThrows(UnavailableCodeException.class, () -> service.valid(payment));
    }

    @Test
    void shouldTogglePayment() {
        // given
        Payment payment = new Payment("code", 100, 0, 0, LocalDate.now().plusDays(1));
        when(repository.findById(anyLong())).thenReturn(Optional.of(payment));
        boolean isAdvancePaid = payment.isAdvancePaid();
        // when
        service.togglePayment(1L);
        // then
        assertNotEquals(isAdvancePaid, payment.isAdvancePaid());
    }

    @Test
    void shouldChangeWholeDataWhenCodesAreNotTheSameAndNewCodeIsNotExists() {
        // given
        when(repository.isCodeAvailable(anyString())).thenReturn(true);
        Payment toUpdate = new Payment("code", 100, 0, 0, LocalDate.now().plusDays(1));
        Payment source = new Payment("newCode", 200, 1, 1, LocalDate.now().plusDays(2));
        // when
        service.update(toUpdate, source);
        // then
        then().paymentObjectHasChangedWholeData(toUpdate, source);
    }

    @Test
    void shouldChangeDataWhenCodesAreTheSame(){
        when(repository.isCodeAvailable(anyString())).thenReturn(true);
        Payment toUpdate = new Payment("code", 100, 0, 0, LocalDate.now().plusDays(1));
        Payment source = new Payment("code", 200, 1, 1, LocalDate.now().plusDays(2));
        // when
        service.update(toUpdate, source);
        // then
        then().paymentObjectHasChangedWholeData(toUpdate, source);
    }
}