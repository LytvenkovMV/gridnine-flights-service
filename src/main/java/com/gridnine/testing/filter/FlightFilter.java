package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.FlightPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightFilter {
    private final List<Flight> flights;

    public FlightFilter(List<Flight> flights) {
        Objects.requireNonNull(flights, "Flight list can not be null");
        if(flights.contains(null)) throw new NullPointerException("Flight list contains null element");

        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Flight> doFilter(FlightPredicate predicate) {
        Objects.requireNonNull(predicate, "Predicate can not be null");

        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (predicate.test(flight)) {
                result.add(flight);
            }
        }

        return result;
    }
}
