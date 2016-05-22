package com.github.watchmaker.io.trackme.android.domain;

import org.joda.time.DateTime;

public class PhoneLocation {

    private final double longitutde;
    private final double latitude;
    private final double altitude;
    private final float speed;
    private final PhoneLocationAccuracy accuracy;
    private final DateTime time;


    public PhoneLocation(double longitutde,
                         double latitude,
                         double altitude,
                         float speed,
                         PhoneLocationAccuracy accuracy,
                         DateTime time) {
        this.longitutde = longitutde;
        this.latitude = latitude;
        this.altitude = altitude;
        this.speed = speed;
        this.accuracy = accuracy;
        this.time = time;
    }

    @Override
    public String toString() {
        return "PhoneLocation{" +
                "accuracy=" + accuracy +
                ", longitutde=" + longitutde +
                ", latitude=" + latitude +
                ", altitude=" + altitude +
                ", speed=" + speed +
                ", time=" + time +
                '}';
    }
}

