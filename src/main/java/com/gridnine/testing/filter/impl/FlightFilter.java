package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.AbstractPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightFilter implements Filter<Flight> {
    private final List<Flight> flights;
    private final boolean[] flags;

    public FlightFilter(List<Flight> flights) {
        Objects.requireNonNull(flights, "Flight list cannot be null");
        if (flights.contains(null)) throw new NullPointerException("Flight list contains null element");

        this.flights = flights;
        this.flags = new boolean[flights.size()];
    }

    @Override
    public Filter<Flight> doFilter(AbstractPredicate<Flight> predicate) {
        Objects.requireNonNull(predicate, "Predicate cannot be null");

        for (int i = 0; i < flags.length; i++) {
            if (!flags[i]) {
                if (!predicate.test(flights.get(i))) {
                    flags[i] = true;
                }
            }
        }
        return this;
    }

    @Override
    public List<Flight> getFiltered() {
        List<Flight> result = new ArrayList<>();

        for (int i = 0; i < flags.length; i++) {
            if (!flags[i]) {
                result.add(flights.get(i));
            }
        }

        return result;
    }
}
