package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.FlightPredicate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class DepartureAfterTargetPredicate implements FlightPredicate {
    private final LocalDateTime targetDateTime;

    public DepartureAfterTargetPredicate(LocalDateTime targetDateTime) {
        Objects.requireNonNull(targetDateTime, "Target date time can not be null");
        this.targetDateTime = targetDateTime;
    }

    @Override
    public boolean test(Flight flight) {
        Optional<LocalDateTime> minDepartureDateTime = flight.getSegments().stream()
                .map(Segment::getDepartureDate)
                .min(LocalDateTime::compareTo);
        return minDepartureDateTime.orElse(LocalDateTime.MIN).isBefore(targetDateTime);
    }
}
