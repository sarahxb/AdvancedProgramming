package org.example;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException { try {

        var continents = new Continent();
        continents.create("Europe");
        Database.getConnection().commit();

System.out.println(continents.findByName("Europe"));
        var countries = new Country();
        int europeId = continents.findByName("Europe");
        countries.create("Romania", "RO", europeId);
        countries.create("Ukraine", "UKR" , europeId);
        Database.getConnection().commit();
        System.out.println(countries.findByName("Romania"));
        System.out.println(countries.findByName("Ukraine"));
        System.out.println(continents.findById(1));
        System.out.println(countries.findById(7));
        System.out.println(countries.findById(8));




        Database.getConnection().close();
    } catch (SQLException e) {
        System.err.println(e);
        Database.getConnection().rollback();
    }



}
}