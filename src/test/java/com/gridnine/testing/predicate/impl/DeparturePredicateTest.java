package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.util.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class DeparturePredicateTest {
    Flight flight;
    LocalDateTime departure1;
    LocalDateTime arrival1;
    LocalDateTime departure2;
    LocalDateTime arrival2;

    @BeforeEach
    void init() {
        departure1 = LocalDateTime.of(2025, 2, 14, 12, 0);
        arrival1 = departure1.plusMinutes(100);
        departure2 = arrival1.plusMinutes(30);
        arrival2 = departure2.plusMinutes(100);
        Segment segment1 = new Segment(departure1, arrival1);
        Segment segment2 = new Segment(departure2, arrival2);
        flight = new Flight(List.of(segment1, segment2));
    }

    @Test
    void when_departure_after_target_then_return_true() {
        LocalDateTime targetDateTime = departure1.minusMinutes(10);

        AbstractPredicate<Flight> predicate = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_departure_before_target_then_return_false() {
        LocalDateTime targetDateTime = departure1.plusMinutes(10);

        AbstractPredicate<Flight> predicate = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_departure_equals_target_then_return_false() {
        LocalDateTime targetDateTime = departure1;

        AbstractPredicate<Flight> predicate = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_second_departure_after_target_then_return_false() {
        LocalDateTime targetDateTime = departure2.minusMinutes(10);

        AbstractPredicate<Flight> predicate = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);

        Assertions.assertFalse(predicate.test(flight));
    }
}