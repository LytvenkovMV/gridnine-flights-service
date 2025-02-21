package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightDurationExceedsPredicate extends AbstractPredicate<Flight> {
    private final long targetMinutes;

    public FlightDurationExceedsPredicate(long targetMinutes) {
        this.targetMinutes = targetMinutes;
    }

    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();

        LocalDateTime departure = segments.stream()
                .map(Segment::getDepartureDate)
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.MIN);

        LocalDateTime arrival = segments.stream()
                .map(Segment::getArrivalDate)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.MAX);

        long duration = Duration.between(departure, arrival).toMinutes();

        return duration > targetMinutes;
    }
}
