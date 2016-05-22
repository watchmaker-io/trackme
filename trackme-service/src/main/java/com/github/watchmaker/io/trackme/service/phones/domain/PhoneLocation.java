package com.github.watchmaker.io.trackme.service.phones.domain;

import com.github.watchmaker.io.trackme.service.phones.api.PhoneLocationAccuracy;
import org.joda.time.DateTime;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table(PhoneLocation.COLUMN_FAMILY)
public class PhoneLocation {
    public static final String COLUMN_FAMILY = "phone_location";

    public static final String USER_ID = "user_Id";
    public static final String TIME = "time";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String ALTITUDE = "altitude";
    public static final String SPEED = "speed";
    public static final String ACCURANCY = "accuracy";

    @PrimaryKeyColumn(
            name = "user_id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private UUID userId;

    @PrimaryKeyColumn(
            ordinal = 0,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private Date time;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column
    private double altitude;

    @Column
    private double speed;

    @Column
    private String accuracy;


    public PhoneLocation(UUID userId,
                         Date time,
                         double longitude,
                         double latitude,
                         double altitude,
                         double speed,
                         String accuracy) {
        this.userId = userId;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.speed = speed;
        this.accuracy = accuracy;
    }

    public UUID getUserId() {
        return userId;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public PhoneLocationAccuracy getPhoneLocationAccuracy() {
        return PhoneLocationAccuracy.from(accuracy);
    }

    public String getAccuracy() {
        return accuracy;
    }

    public DateTime getTime() {
        return new DateTime(time);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID userId;
        private double longitude;
        private double latitude;
        private double altitude;
        private double speed;
        private PhoneLocationAccuracy accuracy;
        private Date time;


        private Builder() {
        }

        public Builder withUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder withAccuracy(String accuracy) {
            this.accuracy = PhoneLocationAccuracy.from(accuracy);
            return this;
        }

        public Builder withAltitude(double altitude) {
            this.altitude = altitude;
            return this;
        }

        public Builder withLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder withLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder withSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public Builder withTime(DateTime time) {
            this.time = new Date(time.getMillis());;
            return this;
        }

        public PhoneLocation build() {
            return new PhoneLocation(userId, time, longitude, latitude, altitude, speed, accuracy.name());
        }
    }
}
