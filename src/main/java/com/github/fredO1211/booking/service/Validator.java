package com.github.fredO1211.booking.service;

public interface Validator<T> {
    T valid(T t);
}
