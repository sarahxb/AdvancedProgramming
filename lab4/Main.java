package org.example;


import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Location> locations = Arrays.asList(
                new Location("F1", LocationType.FRIENDLY),
                new Location("E1", LocationType.ENEMY),
                new Location("N1", LocationType.NEUTRAL),
                new Location("F2", LocationType.FRIENDLY),
                new Location("E2", LocationType.ENEMY),
                new Location("N2", LocationType.NEUTRAL)
        );


        TreeSet<Location> friendlyLocations = locations.stream()
                .filter(loc -> loc.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly Locations (sorted by name):");
        friendlyLocations.forEach(System.out::println);


        LinkedList<Location> enemyLocations = locations.stream()
                .filter(loc -> loc.getType() == LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("\nEnemy Locations (sorted by type, then name):");
        enemyLocations.forEach(System.out::println);

    }
}