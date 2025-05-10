package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class DataImporter {

    public void importCapitals(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);

                String countryName = fields[0].trim();
                String capitalName = fields[1].trim();
                double latitude = Double.parseDouble(fields[2].trim());
                double longitude = Double.parseDouble(fields[3].trim());
                String countryCode = fields[4].trim();
                String continentName = fields[5].trim();

                int continentId = getOrCreateContinent(continentName);
                int countryId = getOrCreateCountry(countryName, countryCode, continentId);
                insertCity(capitalName, countryId, true, latitude, longitude);
            }

            Database.getConnection().commit();
            System.out.println("Data imported successfully.");

        } catch (Exception e) {
            System.err.println("Import failed: " + e.getMessage());
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback failed: " + ex.getMessage());
            }
        }
    }

    private int getOrCreateContinent(String name) throws SQLException {
        String select = "SELECT id FROM continents WHERE name = ?";
        String insert = "INSERT INTO continents (name) VALUES (?)";

        try (Connection con = Database.getConnection();
             PreparedStatement stmt = con.prepareStatement(select)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("id");
        }

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }

        throw new SQLException("Failed to insert continent: " + name);
    }

    private int getOrCreateCountry(String name, String code, int continentId) throws SQLException {
        String select = "SELECT id FROM countries WHERE name = ?";
        String insert = "INSERT INTO countries (name, code, continent) VALUES (?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement stmt = con.prepareStatement(select)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("id");
        }

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, code.isEmpty() ? null : code);
            stmt.setInt(3, continentId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }

        throw new SQLException("Failed to insert country: " + name);
    }

    private void insertCity(String name, int countryId, boolean capital, double lat, double lon) throws SQLException {
        String insert = "INSERT INTO cities (name, country, capital, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(insert)) {
            stmt.setString(1, name);
            stmt.setInt(2, countryId);
            stmt.setBoolean(3, capital);
            stmt.setDouble(4, lat);
            stmt.setDouble(5, lon);
            stmt.executeUpdate();
        }
    }
}

