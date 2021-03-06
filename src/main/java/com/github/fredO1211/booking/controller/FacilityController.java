package com.github.fredO1211.booking.controller;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.service.FacilityService;
import com.github.fredO1211.booking.service.exceptions.IncorrectInputDataException;
import com.github.fredO1211.booking.service.exceptions.UnavailableNameException;
import com.github.fredO1211.booking.service.impl.FacilityServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/facilities")
public class FacilityController {
    final FacilityService service;

    public FacilityController(FacilityServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    ResponseEntity<List<Facility>> readAllFacilities() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping("/page/{index}")
    ResponseEntity<List<Facility>> readAllFacilities(@PathVariable int index) {
        PageRequest page = PageRequest.of(index, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @PostMapping
    ResponseEntity<?> createFacility(@RequestBody Facility toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } catch (UnavailableNameException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateFacility(@PathVariable Long id, @RequestBody Facility toUpdate) {
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
    ResponseEntity<?> deleteFacility(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
