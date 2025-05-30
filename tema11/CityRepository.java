package org.example.lab11;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepository {

    public List<CityDTO> findAll() throws SQLException {
        List<CityDTO> cities = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, country, capital, latitude, longitude FROM cities")) {
            while (rs.next()) {
                cities.add(new CityDTO(
                        rs.getString("name"),
                        rs.getInt("country"),
                        rs.getBoolean("capital"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                ));
            }
        }
        return cities;
    }

    public void create(City city) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO cities (name, country, capital, latitude, longitude) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, city.getName());
            pstmt.setInt(2, city.getCountry());
            pstmt.setBoolean(3, city.isCapital());
            pstmt.setDouble(4, city.getLatitude());
            pstmt.setDouble(5, city.getLongitude());
            pstmt.executeUpdate();
            con.commit();
        }
    }

    public void updateName(int id, String newName) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE cities SET name = ? WHERE id = ?")) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            con.commit();
        }
    }

    public void delete(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM cities WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
        }
    }
}