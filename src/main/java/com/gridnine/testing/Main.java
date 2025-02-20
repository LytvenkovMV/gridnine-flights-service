package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.predicate.FlightPredicate;
import com.gridnine.testing.predicate.impl.ArrivalBeforeDeparturePredicate;
import com.gridnine.testing.predicate.impl.DepartureAfterTargetPredicate;
import com.gridnine.testing.predicate.impl.GroundTimeExceedsTargetPredicate;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter filter = new FlightFilter(flights);

        LocalDateTime targetDateTime = LocalDateTime.now();
        FlightPredicate predicate1 = new DepartureAfterTargetPredicate(targetDateTime);
        System.out.println("Filter flights with departure after " + targetDateTime);
        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filter.doFilter(predicate1));

        System.out.println("Filter flights with arrival before departure");
        FlightPredicate predicate2 = new ArrivalBeforeDeparturePredicate();
        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filter.doFilter(predicate2));

        long targetGroundMinutes = 120;
        FlightPredicate predicate3 = new GroundTimeExceedsTargetPredicate(targetGroundMinutes);
        System.out.printf("Filter flights with ground time greater then %d minutes\n", targetGroundMinutes);
        System.out.println("Initial list:  " + filter.getFlights());
        System.out.println("Filtered list: " + filter.doFilter(predicate3));
    }
}