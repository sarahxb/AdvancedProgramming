import java.time.LocalTime;
import java.util.*;

public class Schedule {
    private Map<Flight, Runway> flightMap = new HashMap<>();

    public boolean assignRunway(List<Runway> runways, Flight flight, List<Flight> scheduledFlights) {
        for (Runway runway : runways) {
            boolean suprapunere = false;

            for (Flight scheduled : flightMap.keySet()) {

                LocalTime scheduledStart = scheduled.getLandingStart();
                LocalTime scheduledEnd = scheduled.getLandingEnd();
                LocalTime flightStart = flight.getLandingStart();
                LocalTime flightEnd = flight.getLandingEnd();


                if (!((scheduledEnd.compareTo(flightStart) < 0) || (flightEnd.compareTo(scheduledStart) < 0))
                        && flightMap.get(scheduled).equals(runway)) {

                    suprapunere = true;
                    break;
                }
            }

            if (!suprapunere) {
                flightMap.put(flight, runway);
                return true;
            }
        }
        return false;
    }


    public void scheduleFlights(Airport airport, List<Flight> flights) {
        flights.sort(Comparator.comparing(Flight::getLandingStart));

        for (Flight flight : flights) {
            if (!assignRunway(airport.getRunways(), flight, new ArrayList<>(flightMap.keySet()))) {
                System.out.println("No available runway for " + flight);
            }
        }
    }

    public void printSchedule() {
        System.out.println("\nFlight Schedule:");
        for (Map.Entry<Flight, Runway> entry : flightMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

