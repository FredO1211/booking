package com.github.fredO1211.booking.service.impl;

import com.github.fredO1211.booking.domain.Booking;
import com.github.fredO1211.booking.domain.Payment;
import com.github.fredO1211.booking.repository.BookingRepository;
import com.github.fredO1211.booking.service.*;
import com.github.fredO1211.booking.service.dto.BookingDTO;
import com.github.fredO1211.booking.service.exceptions.EntityNotFoundException;
import com.github.fredO1211.booking.service.exceptions.UnavailableDateException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService, Validator<Booking> {
    public final BookingRepository repository;
    public final GuestService guestService;
    public final PaymentService paymentService;
    public final MailProvider provider;

    public BookingServiceImpl(BookingRepository repository, GuestServiceImpl guestService, PaymentServiceImpl paymentService, EmailProvider provider) {
        this.repository = repository;
        this.guestService = guestService;
        this.paymentService = paymentService;
        this.provider = provider;
    }

    @Override
    public Booking save(@Valid Booking booking) {
        Payment paymentToSave = booking.getPayment();
        paymentService.save(paymentToSave);
        try {
            if (booking.getGuest().getId() == null) {
                guestService.save(booking.getGuest());
            }

            BookingValidator.validNewBooking(booking,paymentToSave);

            return repository.save(valid(booking));
        } catch (Exception e) {
            paymentService.delete(booking.getPayment());
            throw e;
        }
    }

    @Override
    public List<Booking> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Booking> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Booking update(Long id, @Valid Booking source) {
        Booking toUpdate = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
        toUpdate.setCountOfGuests(source.getCountOfGuests());
        toUpdate.setStartOfBooking(source.getStartOfBooking());
        toUpdate.setEndOfBooking(source.getEndOfBooking());
        toUpdate.setFacility(source.getFacility());
        return toUpdate;
    }

    public Booking update(Booking toUpdate, @Valid BookingDTO source) {
        valid(toUpdate, source);
        toUpdate.setCountOfGuests(source.getCountOfGuests());
        toUpdate.setStartOfBooking(source.getStartOfBooking());
        toUpdate.setEndOfBooking(source.getEndOfBooking());
        return repository.save(toUpdate);
    }

    @Override
    public Booking update(Long id, BookingDTO source) {
        Booking toUpdate = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
        return update(toUpdate, source);
    }

    @Override
    public void delete(Booking booking) {
        repository.delete(booking);
    }


    public void delete(Long id) {
        repository.findById(id).ifPresent(this::delete);
    }

    @Override
    public Booking valid(Booking booking) {
        if (!repository.isAvailable(
                booking.getStartOfBooking().toString(),
                booking.getEndOfBooking().toString(),
                booking.getFacility().getId())) {
            throw new UnavailableDateException();
        }
        return booking;
    }

    public BookingDTO valid(Booking currentBooking,BookingDTO source) {
        List<LocalDate> datesToCheck = DateProvider.getOtherDates(
                currentBooking.getStartOfBooking(),
                currentBooking.getEndOfBooking(),
                source.getStartOfBooking(),
                source.getEndOfBooking());

        datesToCheck.forEach(d->{
            if(repository.isAvailable(d.toString(),currentBooking.getId())){
                throw new UnavailableDateException();
            }
        });
        return source;
    }
}
