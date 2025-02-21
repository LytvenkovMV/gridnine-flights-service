package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class FlightDurationExceedsPredicateTest {
    long interval;
    Flight flight;

    @BeforeEach
    void init() {
        interval = 60;
        LocalDateTime departure1 = LocalDateTime.now();
        LocalDateTime arrival1 = departure1.plusMinutes(interval);
        LocalDateTime departure2 = arrival1.plusMinutes(interval);
        LocalDateTime arrival2 = departure2.plusMinutes(interval);
        Segment segment1 = new Segment(departure1, arrival1);
        Segment segment2 = new Segment(departure2, arrival2);
        flight = new Flight(List.of(segment1, segment2));
    }

    @Test
    void when_flight_duration_exceeds_target_then_return_true() {
        long target = interval * 3 - 10;

        AbstractPredicate<Flight> predicate = new FlightDurationExceedsPredicate(target);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_flight_duration_does_not_exceed_target_then_return_false() {
        long target = interval * 3 + 10;

        AbstractPredicate<Flight> predicate = new FlightDurationExceedsPredicate(target);

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_flight_duration_equals_target_then_return_false() {
        long target = interval * 3;

        AbstractPredicate<Flight> predicate = new FlightDurationExceedsPredicate(target);

        Assertions.assertFalse(predicate.test(flight));
    }
}