package com.gridnine.testing.filter;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.FlightPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class FlightFilterTest {

    List<Flight> flights;
    FlightFilter filter;

    @BeforeEach
    void init() {
        flights = FlightBuilder.createFlights();
        filter = new FlightFilter(flights);
    }

    @Test
    void when_predicate_returns_always_true_then_return_all_flights() {
        FlightPredicate predicate = new FlightPredicate() {
            @Override
            public boolean test(Flight flight) {
                return true;
            }
        };

        List<Flight> result = filter.doFilter(predicate);

        Assertions.assertEquals(flights, result);
    }

    @Test
    void when_predicate_returns_always_false_then_return_empty_list() {
        FlightPredicate predicate = new FlightPredicate() {
            @Override
            public boolean test(Flight flight) {
                return false;
            }
        };

        List<Flight> result = filter.doFilter(predicate);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void when_construct_with_null_parameter_then_exception() {

        Assertions.assertThrows(NullPointerException.class, () -> new FlightFilter(null));
    }

    @Test
    void when_doFilter_with_null_parameter_then_exception() {

        Assertions.assertThrows(NullPointerException.class, () -> filter.doFilter(null));
    }
}