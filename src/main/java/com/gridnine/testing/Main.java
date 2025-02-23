package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.impl.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.AbstractPredicate;
import com.gridnine.testing.predicate.Operator;
import com.gridnine.testing.predicate.impl.ArrivalBeforeDeparturePredicate;
import com.gridnine.testing.predicate.impl.DeparturePredicate;
import com.gridnine.testing.predicate.impl.GroundTimePredicate;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Initial flight list:");
        System.out.println(flights);

        LocalDateTime targetDateTime = LocalDateTime.now();
        AbstractPredicate<Flight> predicate1 = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);
        System.out.println("Flights with departure after " + targetDateTime + ":");
        System.out.println(new FlightFilter(flights).doFilter(predicate1).getFiltered());

        AbstractPredicate<Flight> predicate2 = new ArrivalBeforeDeparturePredicate();
        System.out.println("Flights with arrival before departure:");
        System.out.println(new FlightFilter(flights).doFilter(predicate2).getFiltered());

        long targetGroundMinutes = 120;
        AbstractPredicate<Flight> predicate3 = new GroundTimePredicate(Operator.GREATER_THAN, targetGroundMinutes);
        System.out.println("Flights with ground time greater then " + targetGroundMinutes + " minutes:");
        System.out.println(new FlightFilter(flights).doFilter(predicate3).getFiltered());
    }
}