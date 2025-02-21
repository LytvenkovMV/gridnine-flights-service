package com.gridnine.testing.filter;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.FlightPredicate;
import com.gridnine.testing.predicate.impl.ArrivalBeforeDeparturePredicate;
import com.gridnine.testing.predicate.impl.DepartureAfterTargetPredicate;
import com.gridnine.testing.predicate.impl.GroundTimeGreaterOrEqualsTargetPredicate;
import com.gridnine.testing.predicate.impl.SegmentNumberGreaterOrEqualsThanTargetPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class FlightFilterUsageExampleTest {

    List<Flight> flights;

    @BeforeEach
    void init() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void filter_usage_example_1() {

        // Создаем предикат, пропускающий только перелеты с датой и временем вылета
        // позже текущей даты
        LocalDateTime targetDateTime = LocalDateTime.now();
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(targetDateTime);

        // Создаем предикат, пропускающий только те перелеты, в которых количество промежуточных
        // рейсов больше или равно 3
        long targetSegmentNumber = 3;
        FlightPredicate predicate2 = new SegmentNumberGreaterOrEqualsThanTargetPredicate(targetSegmentNumber);

        // Создаем комбинированый предикат, пропускающий только те перелеты, в которых дата и время
        // вылета позже текущей даты и количество промежуточных рейсов меньше 3
        FlightPredicate combinedPredicate = predicate1.and(predicate2.negate());

        // Создаем фильтр. Подаем в конструктор исходный массив перелетов
        FlightFilter filter = new FlightFilter(flights);

        // Вызываем метод doFilter(), подаем в него предикат и получаем отфильтрованный список
        List<Flight> filtered = filter.doFilter(combinedPredicate);

        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filtered);
    }

    @Test
    void filter_usage_example_2() {
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(LocalDateTime.MIN);
        FlightPredicate predicate2 = new ArrivalBeforeDeparturePredicate();
        FlightPredicate predicate3 = new GroundTimeGreaterOrEqualsTargetPredicate(120);
        FlightPredicate combinedPredicate = predicate1.and(predicate2).and(predicate3);
        FlightFilter filter = new FlightFilter(flights);

        List<Flight> filtered = filter.doFilter(combinedPredicate);

        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filtered);
    }

    @Test
    void filter_usage_example_3() {
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(LocalDateTime.MIN);
        FlightPredicate predicate2 = new ArrivalBeforeDeparturePredicate();
        FlightPredicate predicate3 = new GroundTimeGreaterOrEqualsTargetPredicate(120);
        FlightPredicate combinedPredicate = (predicate1.negate()).and(predicate2.negate()).and(predicate3.negate());
        FlightFilter filter = new FlightFilter(flights);

        List<Flight> filtered = filter.doFilter(combinedPredicate);

        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filtered);
    }
}