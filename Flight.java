import java.time.LocalTime;

public class Flight {
    private String id;
    private Aircraft aircraft;
    private LocalTime landingStart;
    private LocalTime landingEnd;

    public Flight(String id, Aircraft aircraft, LocalTime landingStart, LocalTime landingEnd) {
        this.id = id;
        this.aircraft = aircraft;
        this.landingStart = landingStart;
        this.landingEnd = landingEnd;
    }

    public String getId() { return id; }
    public Aircraft getAircraft() { return aircraft; }
    public LocalTime getLandingStart() { return landingStart; }
    public LocalTime getLandingEnd() { return landingEnd; }



    @Override
    public String toString() {
        return "Flight " + id + " (" + aircraft.getName() + ") landing: " + landingStart + " - " + landingEnd;
    }
}
