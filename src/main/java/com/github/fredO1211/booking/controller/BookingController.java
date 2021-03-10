package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.service.BookingService;
import com.github.fredO1211.booking.service.FacilityService;
import com.github.fredO1211.booking.service.exceptions.IncorrectInputDataException;
import com.github.fredO1211.booking.service.exceptions.UnavailableNameException;
import com.github.fredO1211.booking.service.impl.BookingServiceImpl;
import com.github.fredO1211.booking.service.impl.FacilityServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bookings")
public class BookingController {
    final BookingService service;

    public BookingController(BookingServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    ResponseEntity<List<Booking>> readAllBookings() {
        PageRequest page = PageRequest.of(0, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/page/{index}")
    ResponseEntity<List<Booking>> readAllBookings(@PathVariable int index) {
        PageRequest page = PageRequest.of(index-1, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/{id}")
    ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<?> createBooking(@RequestBody Booking toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } catch (RuntimeException e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking toUpdate) {
        try {
            service.update(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (UnavailableNameException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
