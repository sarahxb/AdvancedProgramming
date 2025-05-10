package org.example;


public class Main {
    public static void main(String[] args) {
        try {
            DistanceTool dt = new DistanceTool();
            dt.displayCityDistances();
            Database.getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
