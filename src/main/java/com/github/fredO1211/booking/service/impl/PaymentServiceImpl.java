package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.service.PaymentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public List<Payment> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Payment> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Payment update(Payment toUpdate, Payment source) {
        return null;
    }

    @Override
    public void delete(Payment payment) {

    }
}
