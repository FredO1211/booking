package com.github.fredO1211.booking.repository;

import com.github.fredO1211.booking.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(nativeQuery = true, value = "SELECT count(*)<1 FROM PAYMENTS WHERE CODE=:code")
    boolean isCodeAvailable(String code);

    @Query(nativeQuery = true, value = "SELECT * FROM PAYMENTS WHERE NOT IS_ADVANCE_PAID")
    Page<Payment> unpaidList(Pageable pageable);

}
