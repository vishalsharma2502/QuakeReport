package com.example.android.quakereport;

public class Earthquake {

    private double magnitude;

    private String city;

    private Long TimeInMilliseconds;

    private String url;

    public Earthquake(double magnitude, String city, Long timeInMilliseconds, String url) {
        this.magnitude = magnitude;
        this.city = city;
        TimeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getCity() {
        return city;
    }

    public Long getTimeInMilliseconds() {
        return TimeInMilliseconds;
    }
}
