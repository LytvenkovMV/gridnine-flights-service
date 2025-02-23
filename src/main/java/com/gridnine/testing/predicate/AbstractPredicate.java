package com.gridnine.testing.predicate;

import java.time.LocalDateTime;
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

    protected boolean compareNums(long number1, long number2, Operator operator) {
        switch (operator) {
            case LESS_THAN -> {
                return number1 < number2;
            }
            case GREATER_THAN -> {
                return number1 > number2;
            }
            case LESS_THAN_OR_EQUAL_TO -> {
                return number1 <= number2;
            }
            case GREATER_THAN_OR_EQUAL_TO -> {
                return number1 >= number2;
            }
            default -> {
                return number1 == number2;
            }
        }
    }

    public boolean compareNums(double number1, double number2, Operator operator) {
        switch (operator) {
            case LESS_THAN -> {
                return number1 < number2;
            }
            case GREATER_THAN -> {
                return number1 > number2;
            }
            case LESS_THAN_OR_EQUAL_TO -> {
                return number1 <= number2;
            }
            case GREATER_THAN_OR_EQUAL_TO -> {
                return number1 >= number2;
            }
            default -> {
                return number1 == number2;
            }
        }
    }

    protected boolean compareDates(LocalDateTime dateTime1, LocalDateTime dateTime2, Operator operator) {
        switch (operator) {
            case LESS_THAN -> {
                return dateTime1.isBefore(dateTime2);
            }
            case GREATER_THAN -> {
                return dateTime1.isAfter(dateTime2);
            }
            case LESS_THAN_OR_EQUAL_TO -> {
                return dateTime1.isBefore(dateTime2) || dateTime1.isEqual(dateTime2);
            }
            case GREATER_THAN_OR_EQUAL_TO -> {
                return dateTime1.isAfter(dateTime2) || dateTime1.isEqual(dateTime2);
            }
            default -> {
                return dateTime1.isEqual(dateTime2);
            }
        }
    }
}
