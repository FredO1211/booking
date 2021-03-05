package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.service.PaymentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment valid(Payment payment) {
        return null;
    }

    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public List<Payment> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Payment update(Payment toUpdate, Payment source) {
        return null;
    }

    @Override
    public void delete(Payment payment) {

    }
}
