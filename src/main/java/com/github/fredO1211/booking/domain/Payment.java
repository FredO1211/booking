package com.github.fredO1211.booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.fredO1211.booking.messageprovider.MessageProvider;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = MessageProvider.PAYMENT_EMPTY_CODE_MSG)
    private String code;
    @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_NIGHT_COST_MSG)
    private int costPerNight;
    @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_DISCOUNT_MSG)
    private int discount;
    @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_ADVANCE_MSG)
    private int advanceSize;
    @Value("false")
    private boolean isAdvancePaid;
    @Future(message = MessageProvider.PAYMENT_DEADLINE_IN_PAST_MSG)
    private LocalDate deadlineForPayment;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    Payment() {
    }

    public Payment(@NotBlank(message = MessageProvider.PAYMENT_EMPTY_CODE_MSG) String code,
                   @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_NIGHT_COST_MSG) int costPerNight,
                   @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_DISCOUNT_MSG) int discount,
                   @PositiveOrZero(message = MessageProvider.PAYMENT_NEGATIVE_ADVANCE_MSG) int advanceSize,
                   LocalDate deadlineForPayment) {
        this.code = code;
        this.costPerNight = costPerNight;
        this.discount = discount;
        this.advanceSize = advanceSize;
        this.deadlineForPayment = deadlineForPayment;
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(int costPerNight) {
        this.costPerNight = costPerNight;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getAdvanceSize() {
        return advanceSize;
    }

    public void setAdvanceSize(int advanceSize) {
        this.advanceSize = advanceSize;
    }

    public boolean isAdvancePaid() {
        return isAdvancePaid;
    }

    public void setIfAdvancePaid(boolean advancePaid) {
        isAdvancePaid = advancePaid;
    }

    public LocalDate getDeadlineForPayment() {
        return deadlineForPayment;
    }

    public void setDeadlineForPayment(LocalDate deadlineForPayment) {
        this.deadlineForPayment = deadlineForPayment;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
