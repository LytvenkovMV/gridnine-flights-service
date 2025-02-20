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
    FlightPredicate predicate1;
    FlightPredicate predicate2;
    FlightPredicate predicate3;

    @BeforeEach
    void init() {
        flights = FlightBuilder.createFlights();

        predicate1 = new DepartureAfterTargetPredicate(LocalDateTime.MIN);
        predicate2 = new ArrivalBeforeDeparturePredicate();
        predicate3 = new GroundTimeExceedsTargetPredicate(120);
    }

    @Test
    void filter_usage_example_1() {
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(LocalDateTime.MIN);
        FlightPredicate predicate2 = new ArrivalBeforeDeparturePredicate();
        FlightPredicate predicate3 = new GroundTimeExceedsTargetPredicate(120);
        FlightFilter filter = new FlightFilter(flights);

        List<Flight> filtered = filter
                .doFilter(predicate1.or(predicate2).or(predicate3));

        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filtered);
    }

    @Test
    void filter_usage_example_2() {
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(LocalDateTime.MIN);
        FlightPredicate predicate2 = new ArrivalBeforeDeparturePredicate();
        FlightPredicate predicate3 = new GroundTimeExceedsTargetPredicate(120);
        FlightFilter filter = new FlightFilter(flights);

        List<Flight> filtered = filter
                .doFilter(predicate1.and(predicate2).and(predicate3));

        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filtered);
    }

    @Test
    void filter_usage_example_3() {
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(LocalDateTime.MIN);
        FlightPredicate predicate2 = new ArrivalBeforeDeparturePredicate();
        FlightPredicate predicate3 = new GroundTimeExceedsTargetPredicate(120);
        FlightFilter filter = new FlightFilter(flights);

        List<Flight> filtered = filter
                .doFilter((predicate1.negate()).and(predicate2.negate()).and(predicate3.negate()));

        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filtered);
    }
}