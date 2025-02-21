package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;

import java.time.LocalDateTime;
import java.util.Objects;

public class DepartureBetweenPredicate extends AbstractPredicate<Flight> {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public DepartureBetweenPredicate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Objects.requireNonNull(startDateTime, "Start datetime cannot be null");
        Objects.requireNonNull(endDateTime, "End datetime cannot be null");
        if (endDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("End datetime cannot be before start");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean test(Flight flight) {
        LocalDateTime minDepartureDateTime = flight.getSegments().stream()
                .map(Segment::getDepartureDate)
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.MIN);
        return minDepartureDateTime.isAfter(startDateTime) && minDepartureDateTime.isBefore(endDateTime);
    }
}
