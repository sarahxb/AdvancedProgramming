import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String name;
    private List<Runway> runways = new ArrayList<>();

    public Airport(String name, int runwayCount) {
        this.name = name;
        for (int i = 1; i <= runwayCount; i++) {
            runways.add(new Runway("R" + i));
        }
    }

    public List<Runway> getRunways() {
        return runways;
    }

    @Override
    public String toString() {
        return "Airport: " + name + " with " + runways.size() + " runways.";
    }
}
