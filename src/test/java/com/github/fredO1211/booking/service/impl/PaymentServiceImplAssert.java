package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Payment;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceImplAssert {

    PaymentServiceImplAssert() {
    }

    static PaymentServiceImplAssert then(){
        return new PaymentServiceImplAssert();
    }

    PaymentServiceImplAssert paymentObjectHasChangedWholeData(Payment actual, Payment expected){

        assertThat(actual.getCode().equals(expected.getCode()) ||
                actual.getCostPerNight() == expected.getCostPerNight() ||
                actual.getDiscount()==expected.getDiscount() ||
                actual.getAdvanceSize() == expected.getAdvanceSize() ||
                actual.getDeadlineForPayment().isEqual(expected.getDeadlineForPayment()))
                .isTrue();
        return this;
    }
}
