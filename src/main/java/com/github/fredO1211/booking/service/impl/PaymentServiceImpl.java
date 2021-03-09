package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.repository.PaymentRepository;
import com.github.fredO1211.booking.service.PaymentService;
import com.github.fredO1211.booking.service.Validator;
import com.github.fredO1211.booking.service.exceptions.EntityNotFoundException;
import com.github.fredO1211.booking.service.exceptions.IncorrectInputDataException;
import com.github.fredO1211.booking.service.exceptions.UnavailableCodeException;
import com.github.fredO1211.booking.service.exceptions.UnavailableNameException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService, Validator<Payment> {
    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment save(@Valid Payment payment) {
        return repository.save(valid(payment));
    }

    @Override
    public List<Payment> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Payment> getById(Long id) {
        return repository.findById(id);
    }

    public Payment update(Payment toUpdate, @Valid Payment source) {
        if(!source.getCode().equals(toUpdate.getCode())){
            valid(source);
            toUpdate.setCode(source.getCode());
        }
        toUpdate.setAdvanceSize(source.getAdvanceSize());
        toUpdate.setCostPerNight(source.getCostPerNight());
        toUpdate.setDeadlineForPayment(toUpdate.getDeadlineForPayment());
        toUpdate.setDiscount(toUpdate.getDiscount());

        return repository.save(toUpdate);
    }

    @Override
    public Payment update(Long id, Payment source) {
        Payment toUpdate = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        try {
            return update(toUpdate, source);
        } catch (Exception e){
            throw new IncorrectInputDataException(e);
        }
    }

    @Override
    public void delete(Payment payment) {
        repository.delete(payment);
    }

    @Override
    public void togglePayment(Long id) {
        var payment = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        payment.setIfAdvancePaid(!payment.isAdvancePaid());
        repository.save(payment);
    }

    @Override
    public Payment valid(Payment payment) {
        if(!repository.isCodeAvailable(payment.getCode())){
            throw new UnavailableNameException();
        }
        return payment;
    }
}
