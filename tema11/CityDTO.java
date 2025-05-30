package org.example.lab11;

public class CityDTO {
    private String name;
    private int country;
    private boolean capital;
    private double latitude;
    private double longitude;


    public CityDTO() {}

    public CityDTO(String name, int country, boolean capital, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public CityDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    @Override
    public String toString() {
        return "CityDTO{name='" + name + "', country=" + country + ", capital=" + capital +
                ", latitude=" + latitude + ", longitude=" + longitude + "}";
    }
}