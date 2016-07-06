package com.github.watchmaker.io.trackme.service.phones.api;


public class PhoneLocationDTO {
    private final String userId;
    private final String time;
    private final String name;
    private final double longitude;
    private final double latitude;
    private final double altitude;
    private final double speed;
    private final String accuracy;


    public PhoneLocationDTO(String userId, String time, String name, double longitude,
                            double latitude, double altitude, double speed, String accuracy) {
        this.userId = userId;
        this.time = time;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.speed = speed;
        this.accuracy = accuracy;
    }

    public String getUserId() {
        return userId;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public static PhoneLocationDTO.Builder builder() {
        return new PhoneLocationDTO.Builder();
    }

    public static class Builder {
        private String userId;
        private String name;
        private double longitude;
        private double latitude;
        private double altitude;
        private double speed;
        private String accuracy;
        private String time;


        private Builder() {
        }

        public PhoneLocationDTO.Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public PhoneLocationDTO.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public PhoneLocationDTO.Builder withAccuracy(String accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        public PhoneLocationDTO.Builder withAltitude(double altitude) {
            this.altitude = altitude;
            return this;
        }

        public PhoneLocationDTO.Builder withLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public PhoneLocationDTO.Builder withLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public PhoneLocationDTO.Builder withSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public PhoneLocationDTO.Builder withTime(String time) {
            this.time = time;
            return this;
        }

        public PhoneLocationDTO build() {
            return new PhoneLocationDTO(userId, time, name, longitude, latitude, altitude, speed, accuracy);
        }
    }
}
