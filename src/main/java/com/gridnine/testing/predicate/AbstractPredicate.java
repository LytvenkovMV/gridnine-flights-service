package com.gridnine.testing.predicate;

import java.util.Objects;

public abstract class AbstractPredicate<T> {

    public abstract boolean test(T t);

    public AbstractPredicate<T> negate() {
        return new AbstractPredicate<>() {
            @Override
            public boolean test(T t) {
                return !AbstractPredicate.this.test(t);
            }
        };
    }

    public AbstractPredicate<T> and(AbstractPredicate<T> other) {
        Objects.requireNonNull(other, "Predicate cannot be null");

        return new AbstractPredicate<>() {
            @Override
            public boolean test(T t) {
                return AbstractPredicate.this.test(t) && other.test(t);
            }
        };
    }

    public AbstractPredicate<T> or(AbstractPredicate<T> other) {
        Objects.requireNonNull(other, "Predicate cannot be null");

        return new AbstractPredicate<>() {
            @Override
            public boolean test(T t) {
                return AbstractPredicate.this.test(t) || other.test(t);
            }
        };
    }
}
