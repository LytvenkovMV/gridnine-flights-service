package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.impl.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.util.Operator;
import com.gridnine.testing.predicate.impl.ArrivalBeforeDeparturePredicate;
import com.gridnine.testing.predicate.impl.DeparturePredicate;
import com.gridnine.testing.predicate.impl.GroundTimePredicate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Список всех перелетов:");
        System.out.println(flights);

        LocalDateTime targetDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        AbstractPredicate<Flight> predicate1 = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);
        System.out.println("Перелеты, у которых отправление позже " + targetDateTime.format(formatter) + ":");
        System.out.println(new FlightFilter(flights).doFilter(predicate1).getFiltered());

        AbstractPredicate<Flight> predicate2 = new ArrivalBeforeDeparturePredicate();
        System.out.println("Перелеты, у которых отправление позже, чем прибытие:");
        System.out.println(new FlightFilter(flights).doFilter(predicate2).getFiltered());

        long targetGroundMinutes = 120;
        AbstractPredicate<Flight> predicate3 = new GroundTimePredicate(Operator.GREATER_THAN, targetGroundMinutes);
        System.out.println("Перелеты, у которых время, проведенное на земле более " + targetGroundMinutes + " минут:");
        System.out.println(new FlightFilter(flights).doFilter(predicate3).getFiltered());
    }
}