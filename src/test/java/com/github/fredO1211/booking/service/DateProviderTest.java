package com.github.fredO1211.booking.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldReturnWholeDatesListBetweenGivenIncludingTheExtremes(){
        // given
        LocalDate startDay = LocalDate.ofYearDay(2020, 1);
        LocalDate endDay = LocalDate.ofYearDay(2020, 5);
        List<LocalDate> result;
        // when
        result = DateProvider.getDatesListIncludingTheExtremes(startDay,endDay);
        // then
        assertEquals(5, result.size());
        assertTrue(result.contains(startDay));
        assertTrue(result.contains(endDay));
    }
    @Test
    void shouldReturnDatesListOtherThanTheStarting(){
        // given
        LocalDate currentStartDay = LocalDate.ofYearDay(2020, 1);
        LocalDate currentEndDay = LocalDate.ofYearDay(2020, 5);
        LocalDate newStartDay = LocalDate.ofYearDay(2020, 3);
        LocalDate newEndDay = LocalDate.ofYearDay(2020, 7);
        List<LocalDate> result;
        // when
        result = DateProvider.getOtherDates(currentStartDay,currentEndDay,newStartDay, newEndDay);
        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(newEndDay));
        assertTrue(result.contains(newEndDay.minusDays(1)));
    }


}
