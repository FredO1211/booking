package com.github.fredO1211.booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateProvider{

    private static void validData(LocalDate startDay, LocalDate endDay){
        if(endDay.isBefore(startDay)){
            throw new IllegalArgumentException();
        }
    }

    public static List<LocalDate> getDatesBetweenOthers(LocalDate startDay, LocalDate endDay){
        validData(startDay,endDay);
        List<LocalDate> dates = startDay.datesUntil(endDay)
                .filter(date->!date.equals(startDay))
                .collect(Collectors.toList());

        dates.forEach(d-> System.out.println(dates));
        return dates;
    }
}
