package com.gridnine.testing.predicate.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ComparatorTest {

    @Test
    void when_compareNums_then_return_true() {
        Assertions.assertTrue(Comparator.compareNums(1, 1, Operator.EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(2, 1, Operator.GREATER_THAN));
        Assertions.assertTrue(Comparator.compareNums(2, 1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(1, 1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(1, 2, Operator.LESS_THAN));
        Assertions.assertTrue(Comparator.compareNums(1, 2, Operator.LESS_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(1, 1, Operator.LESS_THAN_OR_EQUAL_TO));

        Assertions.assertTrue(Comparator.compareNums(1.1, 1.1, Operator.EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(1.2, 1.1, Operator.GREATER_THAN));
        Assertions.assertTrue(Comparator.compareNums(1.2, 1.1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(1.1, 1.1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(1.1, 1.2, Operator.LESS_THAN));
        Assertions.assertTrue(Comparator.compareNums(1.1, 1.2, Operator.LESS_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareNums(1.1, 1.1, Operator.LESS_THAN_OR_EQUAL_TO));
    }

    @Test
    void when_compareNums_then_return_false() {
        Assertions.assertFalse(Comparator.compareNums(1, 2, Operator.EQUAL_TO));
        Assertions.assertFalse(Comparator.compareNums(1, 2, Operator.GREATER_THAN));
        Assertions.assertFalse(Comparator.compareNums(1, 2, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertFalse(Comparator.compareNums(2, 1, Operator.LESS_THAN));
        Assertions.assertFalse(Comparator.compareNums(2, 1, Operator.LESS_THAN_OR_EQUAL_TO));

        Assertions.assertFalse(Comparator.compareNums(1.1, 1.2, Operator.EQUAL_TO));
        Assertions.assertFalse(Comparator.compareNums(1.1, 1.2, Operator.GREATER_THAN));
        Assertions.assertFalse(Comparator.compareNums(1.1, 1.2, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertFalse(Comparator.compareNums(1.2, 1.1, Operator.LESS_THAN));
        Assertions.assertFalse(Comparator.compareNums(1.2, 1.1, Operator.LESS_THAN_OR_EQUAL_TO));
    }

    @Test
    void when_compareDates_then_return_true() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2025, 2, 14, 12, 0);
        LocalDateTime localDateTime2 = localDateTime1.plusMinutes(5);

        Assertions.assertTrue(Comparator.compareDates(localDateTime1, localDateTime1, Operator.EQUAL_TO));
        Assertions.assertTrue(Comparator.compareDates(localDateTime2, localDateTime1, Operator.GREATER_THAN));
        Assertions.assertTrue(Comparator.compareDates(localDateTime2, localDateTime1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareDates(localDateTime1, localDateTime1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareDates(localDateTime1, localDateTime2, Operator.LESS_THAN));
        Assertions.assertTrue(Comparator.compareDates(localDateTime1, localDateTime2, Operator.LESS_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compareDates(localDateTime1, localDateTime1, Operator.LESS_THAN_OR_EQUAL_TO));
    }

    @Test
    void when_compareDates_then_return_false() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2025, 2, 14, 12, 0);
        LocalDateTime localDateTime2 = localDateTime1.plusMinutes(5);

        Assertions.assertFalse(Comparator.compareDates(localDateTime1, localDateTime2, Operator.EQUAL_TO));
        Assertions.assertFalse(Comparator.compareDates(localDateTime1, localDateTime2, Operator.GREATER_THAN));
        Assertions.assertFalse(Comparator.compareDates(localDateTime1, localDateTime2, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertFalse(Comparator.compareDates(localDateTime2, localDateTime1, Operator.LESS_THAN));
        Assertions.assertFalse(Comparator.compareDates(localDateTime2, localDateTime1, Operator.LESS_THAN_OR_EQUAL_TO));
    }
}