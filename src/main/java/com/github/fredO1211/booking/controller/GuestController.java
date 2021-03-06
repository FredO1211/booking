package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.service.impl.GuestServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

public class GuestController {
    final GuestServiceImpl service;

    public GuestController(GuestServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    ResponseEntity<List<Guest>> readAllBookings() {
        PageRequest page = PageRequest.of(0,12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/page/{index}")
    ResponseEntity<List<Guest>> readAllBookings(@PathVariable int index) {
        PageRequest page = PageRequest.of(index,12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @PostMapping
    ResponseEntity<?> createFacility(@RequestBody Guest toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } catch (IllegalArgumentException | ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateFacility(@PathVariable Long id, @RequestBody Guest toUpdate) {
        try {
            service.update(id,toUpdate);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFacility(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
