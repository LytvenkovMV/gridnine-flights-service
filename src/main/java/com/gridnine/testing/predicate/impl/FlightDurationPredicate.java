package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.Operator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightDurationPredicate extends AbstractPredicate<Flight> {
    private final Operator operator;
    private final long targetMinutes;

    public FlightDurationPredicate(Operator operator, long targetMinutes) {
        this.operator = operator;
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

        return compareNums(duration, targetMinutes, operator);
    }
}
