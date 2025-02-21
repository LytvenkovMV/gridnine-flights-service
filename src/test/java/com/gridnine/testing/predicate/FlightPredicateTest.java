package com.gridnine.testing.predicate;

import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FlightPredicateTest {
    FlightPredicate alwaysTruePredicate;
    FlightPredicate alwaysFalsePredicate;
    Flight flight;

    @BeforeEach
    void init() {
        alwaysTruePredicate = new FlightPredicate() {
            @Override
            public boolean test(Flight f) {
                return true;
            }
        };

        alwaysFalsePredicate = new FlightPredicate() {
            @Override
            public boolean test(Flight f) {
                return false;
            }
        };

        flight = new Flight(List.of());
    }

    @Test
    void when_negate_true_then_return_false() {
        FlightPredicate predicate = alwaysTruePredicate.negate();

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_true_and_true_then_return_true() {
        FlightPredicate predicate = alwaysTruePredicate.and(alwaysTruePredicate);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_true_and_false_then_return_false() {
        FlightPredicate predicate = alwaysTruePredicate.and(alwaysFalsePredicate);

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_and_argument_is_null_then_exception() {

        Assertions.assertThrows(NullPointerException.class, () -> alwaysFalsePredicate.and(null));
    }

    @Test
    void when_true_or_false_then_return_true() {
        FlightPredicate predicate = alwaysTruePredicate.or(alwaysFalsePredicate);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_false_or_false_then_return_false() {
        FlightPredicate predicate = alwaysFalsePredicate.or(alwaysFalsePredicate);

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_or_argument_is_null_then_exception() {

        Assertions.assertThrows(NullPointerException.class, () -> alwaysFalsePredicate.or(null));
    }
}