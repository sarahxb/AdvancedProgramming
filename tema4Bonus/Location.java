package org.example;
import java.util.Objects;

public class Location implements Comparable<Location> {
    private String name;
    private LocationType type;

    public Location(String name, LocationType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public LocationType getType() {
        return type;
    }

    @Override
    public int compareTo(Location other) {
        int nameComparison = this.name.compareTo(other.name);
        return (nameComparison != 0) ? nameComparison : this.type.compareTo(other.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return name.equals(location.name) && type == location.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Location{name='" + name + "', type=" + type + "}";
    }
}
