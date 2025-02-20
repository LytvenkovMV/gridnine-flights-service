package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.FlightPredicate;

import java.util.List;
import java.util.Objects;

public class FlightFilter {
    private List<Flight> flights;

    public FlightFilter(List<Flight> flights) {
        Objects.requireNonNull(flights, "Flight list can not be null");
        this.flights = flights;
    }

    public FlightFilter doFilter(FlightPredicate predicate) {
        Objects.requireNonNull(predicate, "Predicate can not be null");
        flights = flights.stream()
                .filter(predicate)
                .toList();
        return this;
    }

    public List<Flight> getResult() {
        return this.flights;
    }
}
