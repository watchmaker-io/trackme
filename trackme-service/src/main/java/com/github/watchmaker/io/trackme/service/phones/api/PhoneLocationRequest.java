package com.github.watchmaker.io.trackme.service.phones.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private DateTime time;


    @JsonCreator
    public PhoneLocationRequest(@JsonProperty("name") String name,
                                @JsonProperty("longitude") Double longitude,
                                @JsonProperty("latitude") Double latitude,
                                @JsonProperty("altitude") Double altitude,
                                @JsonProperty("speed") Double speed,
                                @JsonProperty("accuracy") String accuracy,
                                @JsonProperty("time") DateTime time
    ) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.speed = speed;
        this.accuracy = accuracy;
        this.time = time;
    }

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
