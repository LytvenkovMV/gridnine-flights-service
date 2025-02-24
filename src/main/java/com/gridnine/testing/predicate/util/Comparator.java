package com.gridnine.testing.predicate.util;

import java.time.LocalDateTime;

public class Comparator {
    public static boolean compareNums(long number1, long number2, Operator operator) {
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

    public static boolean compareNums(double number1, double number2, Operator operator) {
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

    public static boolean compareDates(LocalDateTime dateTime1, LocalDateTime dateTime2, Operator operator) {
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
