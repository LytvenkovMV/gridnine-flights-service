package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class ArrivalBeforeDeparturePredicateTest {

    @Test
    void when_arrival_before_departure_then_return_true() {
        LocalDateTime departure1 = LocalDateTime.now();
        LocalDateTime arrival1 = departure1.plusMinutes(100);
        LocalDateTime departure2 = arrival1.plusMinutes(30);
        LocalDateTime arrival2 = departure2.minusMinutes(10);
        Segment segment1 = new Segment(departure1, arrival1);
        Segment segment2 = new Segment(departure2, arrival2);
        Flight flight = new Flight(List.of(segment1, segment2));

        AbstractPredicate<Flight> predicate = new ArrivalBeforeDeparturePredicate();

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_arrival_after_departure_then_return_false() {
        LocalDateTime departure1 = LocalDateTime.now();
        LocalDateTime arrival1 = departure1.plusMinutes(100);
        LocalDateTime departure2 = arrival1.plusMinutes(30);
        LocalDateTime arrival2 = departure2.plusMinutes(10);
        Segment segment1 = new Segment(departure1, arrival1);
        Segment segment2 = new Segment(departure2, arrival2);
        Flight flight = new Flight(List.of(segment1, segment2));

        AbstractPredicate<Flight> predicate = new ArrivalBeforeDeparturePredicate();

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_arrival_equals_departure_then_return_false() {
        LocalDateTime departure1 = LocalDateTime.now();
        LocalDateTime departure2 = departure1.plusMinutes(30);
        LocalDateTime arrival2 = departure2.plusMinutes(10);
        Segment segment1 = new Segment(departure1, departure1);
        Segment segment2 = new Segment(departure2, arrival2);
        Flight flight = new Flight(List.of(segment1, segment2));

        AbstractPredicate<Flight> predicate = new ArrivalBeforeDeparturePredicate();

        Assertions.assertFalse(predicate.test(flight));
    }
}