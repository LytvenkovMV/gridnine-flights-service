package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class SegmentNumberGreaterOrEqualsPredicateTest {
    Flight flight;

    @BeforeEach
    void init() {
        LocalDateTime departure = LocalDateTime.now();
        LocalDateTime arrival = LocalDateTime.now().plusMinutes(100);
        Segment segment1 = new Segment(departure, arrival);
        Segment segment2 = new Segment(departure, arrival);
        flight = new Flight(List.of(segment1, segment2));
    }

    @Test
    void when_actual_segment_number_greater_than_target_then_return_true() {
        long targetSegmentNumber = 1L;

        AbstractPredicate<Flight> predicate = new SegmentNumberGreaterOrEqualsPredicate(targetSegmentNumber);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_actual_segment_number_equals_target_then_return_true() {
        long targetSegmentNumber = 2L;

        AbstractPredicate<Flight> predicate = new SegmentNumberGreaterOrEqualsPredicate(targetSegmentNumber);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_actual_segment_number_less_than_target_then_return_false() {
        long targetSegmentNumber = 3L;

        AbstractPredicate<Flight> predicate = new SegmentNumberGreaterOrEqualsPredicate(targetSegmentNumber);

        Assertions.assertFalse(predicate.test(flight));
    }
}