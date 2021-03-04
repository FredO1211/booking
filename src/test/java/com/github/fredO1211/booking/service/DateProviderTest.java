package com.github.fredO1211.booking.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateProviderTest {

    @Test
    void shouldReturnListOfDatesBetweenGivenDates() {
        // given
        LocalDate startDay = LocalDate.ofYearDay(2020, 1);
        LocalDate endDay = LocalDate.ofYearDay(2020, 5);
        List<LocalDate> dates;
        // when
        dates = DateProvider.getDatesBetweenOthers(startDay, endDay);
        // then
        assertEquals(3, dates.size());
    }
    @Test
    void shouldReturnEmptyListWhenStartAndEndDateIsTheSame() {
        // given
        LocalDate date = LocalDate.ofYearDay(2020, 1);
        List<LocalDate> dates;
        // when
        dates = DateProvider.getDatesBetweenOthers(date, date);
        // then
        assertEquals(0, dates.size());
    }

    @Test
    void shouldThrowExceptionWhenEndIsBeforeStart() {
        // given
        LocalDate startDay = LocalDate.ofYearDay(2020, 5);
        LocalDate endDay = LocalDate.ofYearDay(2020, 1);
        List<LocalDate> dates;
        // when + then
        assertThrows(IllegalArgumentException.class, ()->{
            DateProvider.getDatesBetweenOthers(startDay,endDay);
        });
    }


}
