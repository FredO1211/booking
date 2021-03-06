package com.github.fredO1211.booking.repository;

import com.github.fredO1211.booking.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM GUESTS where name like %:name% and phone_number like %:phoneNumber% and email like %:email%")
    List<Guest> findGuestByProps(String name, String phoneNumber, String email);
}
