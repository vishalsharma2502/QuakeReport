package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {

    private String magnitude;

    private String city;

    private String date;

    public Earthquake(String magnitude, String city, String date) {
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }
}
