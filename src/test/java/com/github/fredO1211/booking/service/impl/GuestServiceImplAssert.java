package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.domain.Payment;

import static org.assertj.core.api.Assertions.assertThat;

class GuestServiceImplAssert {

    GuestServiceImplAssert() {
    }

    static GuestServiceImplAssert then() {
        return new GuestServiceImplAssert();
    }

    GuestServiceImplAssert guestObjectHasChangedWholeData(Guest actual, Guest expected) {
        assertThat(actual.getName().equals(expected.getName()) ||
                actual.getEmail().equals(expected.getEmail()) ||
                actual.getPhoneNumber().equals(expected.getPhoneNumber()) ||
                actual.getAdditionalInformation().equals(expected.getAdditionalInformation()))
                .isTrue();
        return this;
    }
}
