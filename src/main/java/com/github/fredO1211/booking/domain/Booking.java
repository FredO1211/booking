package com.github.fredO1211.booking.domain;

import com.github.fredO1211.booking.messageprovider.MessageProvider;
import com.github.fredO1211.booking.service.dto.BookingDTO;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
    @Future(message = MessageProvider.START_IN_PAST_MSG)
    private LocalDate startOfBooking;
    @Future(message = MessageProvider.END_IN_PAST_MSG)
    private LocalDate endOfBooking;
    private String description;
    private int countOfGuests;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    public Booking() {
    }

    public Booking(Guest guest, LocalDate startOfBooking, LocalDate endOfBooking, String description, Payment payment, Facility facility) {
        this.guest = guest;
        this.startOfBooking = startOfBooking;
        this.endOfBooking = endOfBooking;
        this.description = description;
        this.payment = payment;
        this.facility = facility;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getStartOfBooking() {
        return startOfBooking;
    }

    public void setStartOfBooking(LocalDate startOfBooking) {
        this.startOfBooking = startOfBooking;
    }

    public LocalDate getEndOfBooking() {
        return endOfBooking;
    }

    public void setEndOfBooking(LocalDate endOfBooking) {
        this.endOfBooking = endOfBooking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public int getCountOfGuests() {
        return countOfGuests;
    }

    public void setCountOfGuests(int countOfGuests) {
        this.countOfGuests = countOfGuests;
    }

    public int getStayLength() {
        return (int) startOfBooking.datesUntil(endOfBooking).count();
    }
}
