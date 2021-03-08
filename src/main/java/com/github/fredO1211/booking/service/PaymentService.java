package com.github.fredO1211.booking.service;

import com.github.fredO1211.booking.domain.Payment;

public interface PaymentService extends CrudService<Payment, Long>{
    public void togglePayment(Long id);
}
