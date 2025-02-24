package com.gridnine.testing.filter;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.impl.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.util.Operator;
import com.gridnine.testing.predicate.impl.DeparturePredicate;
import com.gridnine.testing.predicate.impl.SegmentNumberPredicate;
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
        AbstractPredicate<Flight> predicate1 = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);

        // Создаем предикат, пропускающий только те перелеты, в которых количество промежуточных
        // рейсов больше или равно 3
        long targetSegmentNumber = 3;
        AbstractPredicate<Flight> predicate2 = new SegmentNumberPredicate(Operator.GREATER_THAN_OR_EQUAL_TO, targetSegmentNumber);

        // Создаем комбинированый предикат, пропускающий только те перелеты, у которых дата и время
        // вылета позже текущей даты и количество промежуточных рейсов меньше 3
        AbstractPredicate<Flight> combinedPredicate = predicate1.and(predicate2.negate());

        // Создаем объект класса FlightFilter, вызываем метод doFilter() с комбинированным предикатом,
        // получаем отфильтрованный список
        List<Flight> filtered = new FlightFilter(flights).doFilter(combinedPredicate).getFiltered();

        System.out.println("Initial list:  " + flights);
        System.out.println("Filtered list: " + filtered);
    }

    @Test
    void filter_usage_example_2() {

        // Задаем параметры для предикатов
        LocalDateTime targetDateTime = LocalDateTime.now();
        long targetSegmentNumber = 3;

        // Создаем фильтр. Подаем в конструктор исходный массив перелетов
        List<Flight> filtered = new FlightFilter(flights)
                // Пропускаем только перелеты с датой и временем вылета позже текущей даты
                .doFilter(new DeparturePredicate(Operator.GREATER_THAN, targetDateTime))
                // Пропускаем только перелеты с количеством промежуточных рейсов меньше 3
                .doFilter(new SegmentNumberPredicate(Operator.GREATER_THAN_OR_EQUAL_TO, targetSegmentNumber).negate())
                // Получаем отфильтрованный список
                .getFiltered();

        System.out.println("Initial list:  " + flights);
        System.out.println("Filtered list: " + filtered);
    }

    @Test
    void filter_usage_example_3() {
        // Сокращенная запись предыдущего кода
        LocalDateTime targetDateTime = LocalDateTime.now();
        long targetSegmentNumber = 3;

        List<Flight> filtered = new FlightFilter(flights)
                .doFilter(new DeparturePredicate(Operator.GREATER_THAN, targetDateTime))
                .doFilter(new SegmentNumberPredicate(Operator.GREATER_THAN_OR_EQUAL_TO, targetSegmentNumber).negate())
                .getFiltered();

        System.out.println("Initial list:  " + flights);
        System.out.println("Filtered list: " + filtered);
    }

    @Test
    void filter_usage_example_4() {
        // Получение промежуточного результата
        LocalDateTime targetDateTime = LocalDateTime.now();
        long targetSegmentNumber = 3;

        Filter<Flight> filter = new FlightFilter(flights);

        // В этом списке будут перелеты с датой и временем вылета позже текущей даты
        List<Flight> filtered1 = filter
                .doFilter(new DeparturePredicate(Operator.GREATER_THAN, targetDateTime))
                .getFiltered();

        // В этом списке будут перелеты, у которых дата и время вылета позже
        // текущей даты и количество промежуточных рейсов меньше 3
        List<Flight> filtered2 = filter
                .doFilter(new SegmentNumberPredicate(Operator.GREATER_THAN_OR_EQUAL_TO, targetSegmentNumber).negate())
                .getFiltered();

        System.out.println("Initial list:   " + flights);
        System.out.println("Filtered list1: " + filtered1);
        System.out.println("Filtered list2: " + filtered2);
    }
}