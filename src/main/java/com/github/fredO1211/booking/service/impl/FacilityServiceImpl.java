package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Facility;
import com.github.fredO1211.booking.messageprovider.MessageProvider;
import com.github.fredO1211.booking.repository.FacilityRepository;
import com.github.fredO1211.booking.service.FacilityService;
import com.github.fredO1211.booking.service.exceptions.UnavailableNameException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService {
    private final FacilityRepository repository;

    public FacilityServiceImpl(FacilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Facility valid(Facility facility) {
        if (repository.isFacilityExists(facility.getName())) {
            throw new UnavailableNameException();
        }
        return facility;
    }

    @Override
    public Facility save(@Valid Facility facility) {
        return repository.save(valid(facility));
    }

    @Override
    public List<Facility> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Facility> getById(Long id) {
        return repository.findById(id);
    }


    @Override
    public Facility update(Facility toUpdate, @Valid Facility source) {
        toUpdate.setName(source.getName());
        toUpdate.setBasicRentAmount(source.getBasicRentAmount());
        return repository.save(toUpdate);
    }

    @Override
    public Facility update(Long id, Facility source) {
        Facility toUpdate = repository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException(MessageProvider.ID_DOES_NOT_EXIST_MSG);
        });
        return update(toUpdate, source);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresent(this::delete);
    }

    @Override
    public void delete(Facility facility) {
        repository.delete(facility);
    }
}
