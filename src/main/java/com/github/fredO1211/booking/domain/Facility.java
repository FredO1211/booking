package com.github.fredO1211.booking.domain;

import com.github.fredO1211.booking.messageprovider.MessageProvider;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = MessageProvider.EMPTY_NAME_MSG)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facility")
    private Set<Booking> bookings;
    @PositiveOrZero(message = MessageProvider.FACILITY_NEGATIVE_RENT_AMOUNT_MSG)
    private Integer basicRentAmount;

    public Facility(@NotBlank String name, @PositiveOrZero int basicRentAmount) {
        this.name = name;
        this.basicRentAmount = basicRentAmount;
    }

    Facility() {
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setBasicRentAmount(Integer basicRentAmount) {
        this.basicRentAmount = basicRentAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Set<Booking> getBookings() {
        return bookings;
    }

    void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Integer getBasicRentAmount() {
        return basicRentAmount;
    }

    public void setBasicRentAmount(int basicRentAmount) {
        this.basicRentAmount = basicRentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return basicRentAmount.equals(facility.basicRentAmount) &&
                Objects.equals(name, facility.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basicRentAmount);
    }
}
