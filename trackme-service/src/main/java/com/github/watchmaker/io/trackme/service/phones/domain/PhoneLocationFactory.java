package com.github.watchmaker.io.trackme.service.phones.domain;

import com.github.watchmaker.io.trackme.service.phones.api.PhoneLocationRequest;

import java.util.UUID;

public class PhoneLocationFactory {

    public static PhoneLocation create(String userId, PhoneLocationRequest request) {
        return PhoneLocation.builder()
                            .withUserId(UUID.fromString(userId))
                            .withAccuracy(request.getAccuracy())
                            .withLongitude(request.getLongitude())
                            .withLatitude(request.getLatitude())
                            .withAltitude(request.getAltitude())
                            .withSpeed(request.getSpeed())
                            .withTime(request.getTime())
                            .build();
    }
}
