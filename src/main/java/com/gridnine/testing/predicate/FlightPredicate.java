package com.gridnine.testing.predicate;

import com.gridnine.testing.model.Flight;

import java.util.Objects;

public abstract class FlightPredicate {

    public abstract boolean test(Flight f);

    public FlightPredicate negate() {
        return new FlightPredicate() {
            @Override
            public boolean test(Flight f) {
                return !FlightPredicate.this.test(f);
            }
        };
    }

    public FlightPredicate and(FlightPredicate other) {
        Objects.requireNonNull(other, "Predicate can not be null");

        return new FlightPredicate() {
            @Override
            public boolean test(Flight f) {
                return FlightPredicate.this.test(f) && other.test(f);
            }
        };
    }

    public FlightPredicate or(FlightPredicate other) {
        Objects.requireNonNull(other, "Predicate can not be null");

        return new FlightPredicate() {
            @Override
            public boolean test(Flight f) {
                return FlightPredicate.this.test(f) || other.test(f);
            }
        };
    }
}
