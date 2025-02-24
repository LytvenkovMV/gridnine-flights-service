package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.util.Comparator;
import com.gridnine.testing.predicate.util.Operator;

import java.time.LocalDateTime;
import java.util.Objects;

public class ArrivalPredicate extends AbstractPredicate<Flight> {
    private final Operator operator;
    private final LocalDateTime targetDateTime;

    public ArrivalPredicate(Operator operator, LocalDateTime targetDateTime) {
        Objects.requireNonNull(targetDateTime, "Target datetime cannot be null");
        this.operator = operator;
        this.targetDateTime = targetDateTime;
    }

    @Override
    public boolean test(Flight flight) {
        LocalDateTime maxArrivalDateTime = flight.getSegments().stream()
                .map(Segment::getArrivalDate)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.MAX);
        return Comparator.compareDates(maxArrivalDateTime, targetDateTime, operator);
    }
}
