package org.example;

import java.sql.*;

public class Country {

    public void create(String name,String code, int continent_id) throws SQLException {

        if (findByName(name) != null) {
            System.out.println("Country already exists: " + name);
            return;
        }
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (name,code,continent) values (?,?,? )")) {
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, continent_id);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById (int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from countries where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }

    }

}