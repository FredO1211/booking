package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.service.exceptions.ElementDoesNotExistException;
import com.github.fredO1211.booking.service.exceptions.IncorrectInputDataException;
import com.github.fredO1211.booking.service.exceptions.UnavailableCodeException;
import com.github.fredO1211.booking.service.impl.PaymentServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/guests")
public class PaymentController {
    final PaymentServiceImpl service;

    public PaymentController(PaymentServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    ResponseEntity<List<Payment>> readAllGuests() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("code"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/page/{index}")
    ResponseEntity<List<Payment>> readAllGuests(@PathVariable int index) {
        PageRequest page = PageRequest.of(index, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/id")
    ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<?> createFacility(@RequestBody Payment toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } catch (UnavailableCodeException e) {
            throw e;
        } catch (Exception e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateFacility(@PathVariable Long id, @RequestBody Payment toUpdate) {
        try {
            service.update(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (ElementDoesNotExistException e){
            throw e;
        } catch (Exception e) {
            throw new IncorrectInputDataException(e);
        }

    }

    @PatchMapping("/{id}")
    ResponseEntity<?> togglePayment(@PathVariable Long id) {
        try {
            return ResponseEntity.noContent().build();
        } catch (ElementDoesNotExistException e){
            throw e;
        }
    }
}
