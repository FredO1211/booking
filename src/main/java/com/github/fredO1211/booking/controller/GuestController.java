package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.domain.Guest;
import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.service.dto.GuestDTO;
import com.github.fredO1211.booking.service.exceptions.ElementDoesNotExistException;
import com.github.fredO1211.booking.service.exceptions.IncorrectInputDataException;
import com.github.fredO1211.booking.service.impl.GuestServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/guests")
public class GuestController {
    final GuestServiceImpl service;

    public GuestController(GuestServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    ResponseEntity<List<Guest>> readAllGuests() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/page/{index}")
    ResponseEntity<List<Guest>> readAllGuests(@PathVariable int index) {
        PageRequest page = PageRequest.of(index, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/id")
    ResponseEntity<Guest> getPaymentById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/_search")
    ResponseEntity<List<Guest>> readGuestsByProps(@RequestBody GuestDTO guest) {
        return ResponseEntity.ok(service.getByProps(guest));
    }

    @PostMapping
    ResponseEntity<?> createFacility(@RequestBody Guest toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } catch (Exception e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateFacility(@PathVariable Long id, @RequestBody Guest toUpdate) {
        try {
            service.update(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (ElementDoesNotExistException e) {
            throw e;
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
