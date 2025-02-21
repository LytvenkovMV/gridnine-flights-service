package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.FlightPredicate;

public class SegmentNumberGreaterOrEqualsThanTargetPredicate extends FlightPredicate {
    private final long targetSegmentNumber;

    public SegmentNumberGreaterOrEqualsThanTargetPredicate(long targetSegmentNumber) {
        this.targetSegmentNumber = targetSegmentNumber;
    }

    @Override
    public boolean test(Flight flight) {
        long segmentNumber = flight.getSegments().size();

        return segmentNumber >= targetSegmentNumber;
    }
}
