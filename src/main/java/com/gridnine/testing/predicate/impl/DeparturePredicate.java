package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.util.Comparator;
import com.gridnine.testing.predicate.util.Operator;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeparturePredicate extends AbstractPredicate<Flight> {
    private final Operator operator;
    private final LocalDateTime targetDateTime;

    public DeparturePredicate(Operator operator, LocalDateTime targetDateTime) {
        Objects.requireNonNull(targetDateTime, "Target datetime cannot be null");
        this.operator = operator;
        this.targetDateTime = targetDateTime;
    }

    @Override
    public boolean test(Flight flight) {
        LocalDateTime minDepartureDateTime = flight.getSegments().stream()
                .map(Segment::getDepartureDate)
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.MIN);
        return Comparator.compare(minDepartureDateTime, targetDateTime, operator);
    }
}
