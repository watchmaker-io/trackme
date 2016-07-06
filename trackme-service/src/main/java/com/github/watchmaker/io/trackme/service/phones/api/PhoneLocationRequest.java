package com.github.watchmaker.io.trackme.service.phones.api;

import org.joda.time.DateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PhoneLocationRequest {

    @NotNull(message = "name must be provided")
    private String name;

    @NotNull(message = "longitude must be provided")
    private Double longitude;

    @NotNull(message = "latitude must be provided")
    private Double latitude;

    private Double altitude;

    @Min(value = 0, message = "speed must be greater or equal 0")
    private Double speed;

    @NotNull(message = "accuracy must be provided")
    private String accuracy;

    @NotNull(message = "time must be provided")
    private DateTime time;


    public String getName() {
        return name;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public Double getAltitude() {
        return altitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public DateTime getTime() {
        return time;
    }
}
