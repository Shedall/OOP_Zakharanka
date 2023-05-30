package com.example.travelaroundbelarus.models;

public class Attraction {
    private double latitude;
    private double longitude;
    private String title;
    private String adres;
    private String description;

    public Attraction(){}

    public Attraction(double latitude, double longitude, String title, String adres, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.adres = adres;
        this.description = description;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public String getAdres() {
        return adres;
    }

    public String getDescription() {
        return description;
    }
}
