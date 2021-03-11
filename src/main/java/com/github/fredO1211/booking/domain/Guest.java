package com.github.fredO1211.booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.fredO1211.booking.messageprovider.MessageProvider;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@EqualsAndHashCode
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;
    @NotBlank
    private String name;
    @Email(message = MessageProvider.INVALID_EMAIL_FORMAT_MSG)
    private String email;
    private String phoneNumber;
    @EqualsAndHashCode.Exclude
    private String additionalInformation;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "guest")
    @EqualsAndHashCode.Exclude
    private Set<Booking> bookings;

    public Guest(@NotBlank String name, @Email(message = MessageProvider.INVALID_EMAIL_FORMAT_MSG) String email, String phoneNumber, String additionalInformation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.additionalInformation = additionalInformation;
    }
}
