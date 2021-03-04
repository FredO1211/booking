package com.github.fredO1211.booking.repository;

import com.github.fredO1211.booking.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    Optional<Facility> findById(Integer id);

    @Query(nativeQuery = true, value = "SELECT count(*)>0 FROM FACILITIES WHERE NAME=:name")
    boolean isFacilityNameExists(String name);
}
