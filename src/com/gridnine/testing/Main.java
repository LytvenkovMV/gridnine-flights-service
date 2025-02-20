package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter filter = new FlightFilter(flights);
        System.out.println(filter
                .doFilter("byArrivalDateBeforeDepartureDate")
                .doFilter("byDepartureDateLaterThan")
                .returnResult());

//        System.out.println(FlightFilter.byDepartureDateLaterThan(flights, LocalDateTime.now()));
//        System.out.println(FlightFilter.byArrivalDateBeforeDepartureDate(flights));
//        System.out.println(FlightFilter.byGroundTimeBiggerThan(flights, 120));
    }
}