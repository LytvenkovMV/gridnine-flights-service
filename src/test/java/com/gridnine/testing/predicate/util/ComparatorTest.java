package com.gridnine.testing.predicate.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ComparatorTest {

    @Test
    void compare_longs() {
        Assertions.assertTrue(Comparator.compare(1, 1, Operator.EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(2, 1, Operator.GREATER_THAN));
        Assertions.assertTrue(Comparator.compare(2, 1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(1, 1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(1, 2, Operator.LESS_THAN));
        Assertions.assertTrue(Comparator.compare(1, 2, Operator.LESS_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(1, 1, Operator.LESS_THAN_OR_EQUAL_TO));

        Assertions.assertFalse(Comparator.compare(1, 2, Operator.EQUAL_TO));
        Assertions.assertFalse(Comparator.compare(1, 2, Operator.GREATER_THAN));
        Assertions.assertFalse(Comparator.compare(1, 2, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertFalse(Comparator.compare(2, 1, Operator.LESS_THAN));
        Assertions.assertFalse(Comparator.compare(2, 1, Operator.LESS_THAN_OR_EQUAL_TO));
    }

    @Test
    void compare_doubles() {
        Assertions.assertTrue(Comparator.compare(1.1, 1.1, Operator.EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(1.2, 1.1, Operator.GREATER_THAN));
        Assertions.assertTrue(Comparator.compare(1.2, 1.1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(1.1, 1.1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(1.1, 1.2, Operator.LESS_THAN));
        Assertions.assertTrue(Comparator.compare(1.1, 1.2, Operator.LESS_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(1.1, 1.1, Operator.LESS_THAN_OR_EQUAL_TO));

        Assertions.assertFalse(Comparator.compare(1.1, 1.2, Operator.EQUAL_TO));
        Assertions.assertFalse(Comparator.compare(1.1, 1.2, Operator.GREATER_THAN));
        Assertions.assertFalse(Comparator.compare(1.1, 1.2, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertFalse(Comparator.compare(1.2, 1.1, Operator.LESS_THAN));
        Assertions.assertFalse(Comparator.compare(1.2, 1.1, Operator.LESS_THAN_OR_EQUAL_TO));
    }

    @Test
    void compare_dates() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2025, 2, 14, 12, 0);
        LocalDateTime localDateTime2 = localDateTime1.plusMinutes(5);

        Assertions.assertTrue(Comparator.compare(localDateTime1, localDateTime1, Operator.EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(localDateTime2, localDateTime1, Operator.GREATER_THAN));
        Assertions.assertTrue(Comparator.compare(localDateTime2, localDateTime1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(localDateTime1, localDateTime1, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(localDateTime1, localDateTime2, Operator.LESS_THAN));
        Assertions.assertTrue(Comparator.compare(localDateTime1, localDateTime2, Operator.LESS_THAN_OR_EQUAL_TO));
        Assertions.assertTrue(Comparator.compare(localDateTime1, localDateTime1, Operator.LESS_THAN_OR_EQUAL_TO));

        Assertions.assertFalse(Comparator.compare(localDateTime1, localDateTime2, Operator.EQUAL_TO));
        Assertions.assertFalse(Comparator.compare(localDateTime1, localDateTime2, Operator.GREATER_THAN));
        Assertions.assertFalse(Comparator.compare(localDateTime1, localDateTime2, Operator.GREATER_THAN_OR_EQUAL_TO));
        Assertions.assertFalse(Comparator.compare(localDateTime2, localDateTime1, Operator.LESS_THAN));
        Assertions.assertFalse(Comparator.compare(localDateTime2, localDateTime1, Operator.LESS_THAN_OR_EQUAL_TO));
    }
}