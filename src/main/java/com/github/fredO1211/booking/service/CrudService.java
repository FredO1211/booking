package com.github.fredO1211.booking.service;

import java.awt.print.Pageable;
import java.util.List;

public interface CrudService<T> {
    T valid(T t);
    T save(T t);
    List<T> getAll(Pageable pageable);
    T update(T t);
    void delete(T t);
}
