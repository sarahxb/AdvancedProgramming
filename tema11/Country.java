package org.example.lab11;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Country {

    public void create(String name, String code, int continent_id) throws SQLException {
        if (findByName(name) != null) {
            System.out.println("Country already exists: " + name);
            return;
        }
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (name,code,continent) values (?,?,?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, continent_id);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select name from countries where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public List<String> findAll() throws SQLException {
        List<String> countries = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select name from countries")) {
            while (rs.next()) {
                countries.add(rs.getString("name"));
            }
        }
        return countries;
    }
}
