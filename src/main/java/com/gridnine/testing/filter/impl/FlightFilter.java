package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.AbstractPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightFilter implements Filter<Flight> {
    private final List<Flight> flights;

    public FlightFilter(List<Flight> flights) {
        Objects.requireNonNull(flights, "Flight list cannot be null");
        if (flights.contains(null)) throw new NullPointerException("Flight list contains null element");

        this.flights = flights;
    }

    @Override
    public List<Flight> doFilter(AbstractPredicate<Flight> predicate) {
        Objects.requireNonNull(predicate, "Predicate cannot be null");

        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (predicate.test(flight)) {
                result.add(flight);
            }
        }

        return result;
    }
}
