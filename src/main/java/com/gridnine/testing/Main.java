package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.impl.DepartureAfterTargetPredicate;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println(new FlightFilter(flights)
                .doFilter(new DepartureAfterTargetPredicate(LocalDateTime.now().plusDays(3)))
                .getResult());
    }
}