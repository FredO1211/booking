package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.repository.FacilityRepository;
import com.github.fredO1211.booking.service.FacilityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    private final FacilityRepository repository;

    public FacilityServiceImpl(FacilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Facility valid(@Valid Facility facility) {
        return facility;
    }

    @Override
    public Facility save(Facility facility) {
        return repository.save(valid(facility));
    }

    @Override
    public List<Facility> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Facility update(Facility toUpdate, Facility source) {
        valid(source);
        toUpdate.setName(source.getName());
        toUpdate.setBasicRentAmount(source.getBasicRentAmount());
        return repository.save(toUpdate);
    }

    @Override
    public void delete(Facility facility) {
        repository.delete(facility);
    }
}
