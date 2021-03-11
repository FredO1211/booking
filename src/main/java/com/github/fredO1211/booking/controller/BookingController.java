package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.service.BookingService;
import com.github.fredO1211.booking.service.FacilityService;
import com.github.fredO1211.booking.service.dto.SimplifiedBookingDTO;
import com.github.fredO1211.booking.service.exceptions.IncorrectInputDataException;
import com.github.fredO1211.booking.service.exceptions.UnavailableNameException;
import com.github.fredO1211.booking.service.impl.BookingServiceImpl;
import com.github.fredO1211.booking.service.impl.FacilityServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.YearMonth;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping()
public class BookingController {
    final BookingService service;

    public BookingController(BookingServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/bookings")
    ResponseEntity<List<Booking>> readAllBookings() {
        PageRequest page = PageRequest.of(0, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/bookings/page/{index}")
    ResponseEntity<List<Booking>> readAllBookings(@PathVariable int index) {
        PageRequest page = PageRequest.of(index-1, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/bookings/{id}")
    ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/facility/{id}/bookings/marks",params = {"date"})
    ResponseEntity<List<SimplifiedBookingDTO>> getSimplifiedBookingDTOByMonth(@PathVariable Long id, @RequestParam YearMonth date) {
        return ResponseEntity.ok(service.getSimplifiedBookingDTOList(date,id));
    }

    @PostMapping("/bookings")
    ResponseEntity<?> createBooking(@RequestBody Booking toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } catch (RuntimeException e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PutMapping("/bookings/{id}")
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

    @DeleteMapping("/bookings/{id}")
    ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
