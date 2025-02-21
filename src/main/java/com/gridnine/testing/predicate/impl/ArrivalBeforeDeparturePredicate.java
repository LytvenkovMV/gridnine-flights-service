package com.gridnine.testing.predicate.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.predicate.AbstractPredicate;

public class ArrivalBeforeDeparturePredicate extends AbstractPredicate<Flight> {

    @Override
    public boolean test(Flight flight) {
        for (Segment s : flight.getSegments()) {
            if (s.getArrivalDate().isBefore(s.getDepartureDate())) {
                return true;
            }
        }
        return false;
    }
}
