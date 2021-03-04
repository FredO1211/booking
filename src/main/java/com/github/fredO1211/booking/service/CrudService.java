package com.github.fredO1211.booking.service;

import java.util.List;

public interface CrudService<T> {
    T valid(T t);
    T save(T t);
    List<T> getAll();
    T update(T t);
    void delete(T t);
}
