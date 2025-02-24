package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.util.Comparator;
import com.gridnine.testing.predicate.util.Operator;

public class SegmentNumberPredicate extends AbstractPredicate<Flight> {
    private final Operator operator;
    private final long targetSegmentNumber;

    public SegmentNumberPredicate(Operator operator, long targetSegmentNumber) {
        this.operator = operator;
        this.targetSegmentNumber = targetSegmentNumber;
    }

    @Override
    public boolean test(Flight flight) {
        long segmentNumber = flight.getSegments().size();

        return Comparator.compare(segmentNumber, targetSegmentNumber, operator);
    }
}
