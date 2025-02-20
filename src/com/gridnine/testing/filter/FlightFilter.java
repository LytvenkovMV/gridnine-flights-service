package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class FlightFilter {

    private List<Flight> flights;
    private Map<String, Predicate<Flight>> filters;

    public FlightFilter(List<Flight> flights) {
        this.flights = flights;
        initFilters();
    }

    public FlightFilter doFilter(String filterKey) {
        flights = flights.stream()
                .filter(filters.get(filterKey))
                .toList();
        return this;
    }

    public List<Flight> returnResult() {
        return this.flights;
    }

    private void initFilters() {
        filters = new HashMap<>();

        filters.put("byDepartureDateLaterThan", f -> {
            LocalDateTime minDepartureDateTime = f.getSegments().stream()
                    .map(Segment::getDepartureDate)
                    .min(LocalDateTime::compareTo)
                    .get();
            return minDepartureDateTime.isAfter(LocalDateTime.now());////////////////////////////////////////////
        });

        filters.put("byArrivalDateBeforeDepartureDate", flight -> {
            for (Segment s : flight.getSegments()) {
                if (s.getArrivalDate().isBefore(s.getDepartureDate())) {
                    return true;
                }
            }
            return false;
        });

        filters.put("byGroundTimeBiggerThan", flight -> {
            List<Segment> segments = flight.getSegments();
            long groundMinutes = 0;
            for (int i = 1; i < segments.size(); i++) {
                LocalDateTime lastArrivalDate = segments.get(i - 1).getArrivalDate();
                LocalDateTime nextDepartureDate = segments.get(i).getDepartureDate();
                Duration duration = Duration.between(lastArrivalDate, nextDepartureDate);
                groundMinutes += duration.toMinutes();
            }
            return groundMinutes > 120;///////////////////////////////////////////////////
        });
    }

//    public static List<Flight> byDepartureDateLaterThan(List<Flight> rawFlights, final LocalDateTime targetDateTime) {
//        return rawFlights.stream()
//                .filter(f -> {
//                    LocalDateTime minDepartureDateTime = f.getSegments().stream()
//                            .map(Segment::getDepartureDate)
//                            .min(LocalDateTime::compareTo)
//                            .get();
//                    return minDepartureDateTime.isAfter(targetDateTime);
//                })
//                .toList();
//    }
//
//    public static List<Flight> byArrivalDateBeforeDepartureDate(List<Flight> rawFlights) {
//        return rawFlights.stream()
//                .filter(flight -> {
//                    for (Segment s : flight.getSegments()) {
//                        if (s.getArrivalDate().isBefore(s.getDepartureDate())) {
//                            return true;
//                        }
//                    }
//                    return false;
//                })
//                .toList();
//    }
//
//    public static List<Flight> byGroundTimeBiggerThan(List<Flight> rawFlights, final long targetGroundMinutes) {
//        return rawFlights.stream()
//                .filter(flight -> {
//                    List<Segment> segments = flight.getSegments();
//                    long groundMinutes = 0;
//                    for (int i = 1; i < segments.size(); i++) {
//                        LocalDateTime lastArrivalDate = segments.get(i - 1).getArrivalDate();
//                        LocalDateTime nextDepartureDate = segments.get(i).getDepartureDate();
//                        Duration duration = Duration.between(lastArrivalDate, nextDepartureDate);
//                        groundMinutes += duration.toMinutes();
//                    }
//                    return groundMinutes > targetGroundMinutes;
//                })
//                .toList();
//    }
}
