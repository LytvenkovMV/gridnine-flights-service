package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class GroundTimeGreaterOrEqualsTargetPredicateTest {
    Flight flight;
    long groundMinutes;

    @BeforeEach
    void init() {
        groundMinutes = 200;
        LocalDateTime departure1 = LocalDateTime.now();
        LocalDateTime arrival1 = departure1.plusMinutes(100);
        LocalDateTime departure2 = arrival1.plusMinutes(groundMinutes);
        LocalDateTime arrival2 = departure2.plusMinutes(100);
        Segment segment1 = new Segment(departure1, arrival1);
        Segment segment2 = new Segment(departure2, arrival2);
        flight = new Flight(List.of(segment1, segment2));
    }

    @Test
    void when_ground_minutes_greater_than_target_then_return_true() {
        long targetMinutes = groundMinutes - 20;

        AbstractPredicate<Flight> predicate = new GroundTimeGreaterOrEqualsTargetPredicate(targetMinutes);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_ground_minutes_equals_target_then_return_true() {
        long targetMinutes = groundMinutes;

        AbstractPredicate<Flight> predicate = new GroundTimeGreaterOrEqualsTargetPredicate(targetMinutes);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_ground_minutes_less_than_target_then_return_false() {
        long targetMinutes = groundMinutes + 20;

        AbstractPredicate<Flight> predicate = new GroundTimeGreaterOrEqualsTargetPredicate(targetMinutes);

        Assertions.assertFalse(predicate.test(flight));
    }
}