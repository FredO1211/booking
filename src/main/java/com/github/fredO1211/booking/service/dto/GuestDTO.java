package com.github.fredO1211.booking.service.dto;

import com.github.fredO1211.booking.domain.Guest;

public class GuestDTO {
    private String name;
    private String email;
    private String phoneNumber;

    GuestDTO(Guest guest) {
        this.name = guest.getName();
        this.email = guest.getEmail();
        this.phoneNumber = guest.getPhoneNumber();
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
