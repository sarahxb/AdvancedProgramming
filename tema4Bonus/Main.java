package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();
        int locationCount = 10;
        Set<String> usedNames = new HashSet<>();
        List<Location> locations = new ArrayList<>();


        while (locations.size() < locationCount) {
            String name = faker.address().city();
            if (usedNames.add(name)) {
                LocationType type = LocationType.values()[random.nextInt(LocationType.values().length)];
                locations.add(new Location(name, type));
            }
        }

        Graph<Location, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        Map<Location, Map<Location, Double>> probabilityGraph = new HashMap<>();


        locations.forEach(location -> {
            graph.addVertex(location);
            probabilityGraph.put(location, new HashMap<>());
        });


        for (int i = 0; i < locations.size(); i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                if (random.nextDouble() < 0.2) {
                    double timeWeight = 1 + random.nextDouble() * 9;
                    double probabilityWeight = 0.1 + random.nextDouble() * 0.9;

                    DefaultWeightedEdge edge = graph.addEdge(locations.get(i), locations.get(j));
                    if (edge != null) {
                        graph.setEdgeWeight(edge, timeWeight);
                        probabilityGraph.get(locations.get(i)).put(locations.get(j), probabilityWeight);
                        probabilityGraph.get(locations.get(j)).put(locations.get(i), probabilityWeight);
                    }
                }
            }
        }

        Location startLocation = locations.get(random.nextInt(locations.size()));
        System.out.println("Start location: " + startLocation);


        DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        System.out.println("\nFastest routes:");
        locations.forEach(location -> {
            if (!location.equals(startLocation) && dijkstraAlg.getPath(startLocation, location) != null) {
                System.out.println(startLocation.getName() + " -> " + location.getName() + " (Distance: " + dijkstraAlg.getPathWeight(startLocation, location) + ")");
            }
        });


        Map<Location, Map<Location, Double>> safestRoutes = floydWarshall(probabilityGraph);
        System.out.println("\nSafest routes:");
        locations.forEach(loc1 -> locations.forEach(loc2 -> {
            if (!loc1.equals(loc2)) {
                System.out.println(loc1.getName() + " -> " + loc2.getName() + " (Safety Probability: " + safestRoutes.get(loc1).get(loc2) + ")");
            }
        }));


        Map<LocationType, Long> locationTypeCount = locations.stream()
                .collect(Collectors.groupingBy(Location::getType, Collectors.counting()));

        System.out.println("\nLocation Types:");
        locationTypeCount.forEach((type, count) -> System.out.println(type + ": " + count));
    }

    public static Map<Location, Map<Location, Double>> floydWarshall(Map<Location, Map<Location, Double>> probabilityGraph) {
        Map<Location, Map<Location, Double>> maxProb = new HashMap<>();

        for (Location loc1 : probabilityGraph.keySet()) {
            maxProb.put(loc1, new HashMap<>());
            for (Location loc2 : probabilityGraph.keySet()) {
                maxProb.get(loc1).put(loc2, loc1.equals(loc2) ? 1.0 : probabilityGraph.getOrDefault(loc1, new HashMap<>()).getOrDefault(loc2, 0.0));
            }
        }

        for (Location k : probabilityGraph.keySet()) {
            for (Location i : probabilityGraph.keySet()) {
                for (Location j : probabilityGraph.keySet()) {
                    double newProb = maxProb.get(i).get(k) * maxProb.get(k).get(j);
                    if (newProb > maxProb.get(i).get(j)) {
                        maxProb.get(i).put(j, newProb);
                    }
                }
            }
        }
        return maxProb;
    }
}
