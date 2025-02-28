package com.gridnine.testing.predicate;

import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AbstractPredicateTest {
    AbstractPredicate<Flight> alwaysTruePredicate;
    AbstractPredicate<Flight> alwaysFalsePredicate;
    Flight flight;

    @BeforeEach
    void init() {
        alwaysTruePredicate = new AbstractPredicate<>() {
            @Override
            public boolean test(Flight f) {
                return true;
            }
        };

        alwaysFalsePredicate = new AbstractPredicate<>() {
            @Override
            public boolean test(Flight f) {
                return false;
            }
        };

        flight = new Flight(List.of());
    }

    @Test
    void when_negate_true_then_return_false() {
        AbstractPredicate<Flight> predicate = alwaysTruePredicate.negate();

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_true_and_true_then_return_true() {
        AbstractPredicate<Flight> predicate = alwaysTruePredicate.and(alwaysTruePredicate);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_true_and_false_then_return_false() {
        AbstractPredicate<Flight> predicate = alwaysTruePredicate.and(alwaysFalsePredicate);

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_and_argument_is_null_then_exception() {

        Assertions.assertThrows(NullPointerException.class, () -> alwaysFalsePredicate.and(null));
    }

    @Test
    void when_true_or_false_then_return_true() {
        AbstractPredicate<Flight> predicate = alwaysTruePredicate.or(alwaysFalsePredicate);

        Assertions.assertTrue(predicate.test(flight));
    }

    @Test
    void when_false_or_false_then_return_false() {
        AbstractPredicate<Flight> predicate = alwaysFalsePredicate.or(alwaysFalsePredicate);

        Assertions.assertFalse(predicate.test(flight));
    }

    @Test
    void when_or_argument_is_null_then_exception() {

        Assertions.assertThrows(NullPointerException.class, () -> alwaysFalsePredicate.or(null));
    }
}