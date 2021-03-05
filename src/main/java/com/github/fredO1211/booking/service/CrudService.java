package com.github.fredO1211.booking.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<T> {
    T valid(T t);
    T save(T t);
    List<T> getAll(Pageable pageable);
    T update(T toUpdate, T source);
    void delete(T t);
}
