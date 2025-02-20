package com.gridnine.testing.filter;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.FlightPredicate;
import com.gridnine.testing.predicate.impl.ArrivalBeforeDeparturePredicate;
import com.gridnine.testing.predicate.impl.DepartureAfterTargetPredicate;
import com.gridnine.testing.predicate.impl.GroundTimeExceedsTargetPredicate;
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
    void filter_usage_example() {
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(LocalDateTime.MIN);
        FlightPredicate predicate2 = new ArrivalBeforeDeparturePredicate();
        FlightPredicate predicate3 = new GroundTimeExceedsTargetPredicate(120);

        FlightFilter filter = new FlightFilter(flights);

        List<Flight> filtered = filter
                .doFilter(predicate1)
                .doFilter(predicate2)
                .doFilter(predicate3)
                .getResult();

        System.out.println(filtered);
    }

    @Test
    void filter_usage_short_example() {
        List<Flight> filtered = new FlightFilter(flights)
                .doFilter(new DepartureAfterTargetPredicate(LocalDateTime.MIN))
                .doFilter(new ArrivalBeforeDeparturePredicate())
                .doFilter(new GroundTimeExceedsTargetPredicate(120))
                .getResult();

        System.out.println(filtered);
    }
}