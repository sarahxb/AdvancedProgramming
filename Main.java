import java.time.LocalTime;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport("Henri", 2);


        Airliner airliner = new Airliner("Boeing 737", "737-800", "N12345", 180);
        Freighter freighter = new Freighter("Boeing 747 Cargo", "747-400F", "C34567", 100000);


        Flight flight1 = new Flight("F101", airliner, LocalTime.of(10, 0), LocalTime.of(10, 30));
        Flight flight2 = new Flight("F102", freighter, LocalTime.of(10, 15), LocalTime.of(10, 45));
        Flight flight3 = new Flight("F103", airliner, LocalTime.of(10, 50), LocalTime.of(11, 20));

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3);


        Schedule schedule = new Schedule();
        schedule.scheduleFlights(airport, flights);
        schedule.printSchedule();
    }
}
