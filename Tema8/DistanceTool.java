package org.example;

import java.sql.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DistanceTool {

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public void displayCityDistances() throws SQLException {
        List<City> cities = getAllCities();

        for (int i = 0; i < cities.size(); i++) {
            for (int j = i + 1; j < cities.size(); j++) {
                City c1 = cities.get(i);
                City c2 = cities.get(j);
                double distance = haversine(c1.getLatitude(), c1.getLongitude(), c2.getLatitude(), c2.getLongitude());
                System.out.printf("Distance between %s and %s: %.2f km%n",
                        c1.getName(), c2.getName(), distance);
            }
        }
    }

    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT id, name, latitude, longitude FROM cities";
        try (Connection con = Database.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                City city = new City(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                );
                cities.add(city);
            }
        }
        return cities;
    }
}
