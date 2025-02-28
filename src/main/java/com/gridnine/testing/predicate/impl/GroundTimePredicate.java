package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.util.Comparator;
import com.gridnine.testing.predicate.util.Operator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GroundTimePredicate extends AbstractPredicate<Flight> {
    private final Operator operator;
    private final long targetMinutes;

    public GroundTimePredicate(Operator operator, long targetMinutes) {
        this.operator = operator;
        this.targetMinutes = targetMinutes;
    }

    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long groundMinutes = 0;

        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime lastArrivalDate = segments.get(i - 1).getArrivalDate();
            LocalDateTime nextDepartureDate = segments.get(i).getDepartureDate();
            Duration duration = Duration.between(lastArrivalDate, nextDepartureDate);
            groundMinutes += duration.toMinutes();
        }
        return Comparator.compare(groundMinutes, targetMinutes, operator);
    }
}
