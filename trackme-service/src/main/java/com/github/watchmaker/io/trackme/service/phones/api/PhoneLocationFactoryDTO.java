package com.github.watchmaker.io.trackme.service.phones.api;

import com.github.watchmaker.io.trackme.service.phones.domain.PhoneLocation;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PhoneLocationFactoryDTO {

    public static PhoneLocationDTO create(PhoneLocation phoneLocation) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");

        return PhoneLocationDTO.builder()
                            .withUserId(phoneLocation.getUserId().toString())
                            .withName(phoneLocation.getName())
                            .withAccuracy(phoneLocation.getAccuracy())
                            .withLongitude(phoneLocation.getLongitude())
                            .withLatitude(phoneLocation.getLatitude())
                            .withAltitude(phoneLocation.getAltitude())
                            .withSpeed(phoneLocation.getSpeed())
                            .withTime(formatter.print(phoneLocation.getTime()))
                            .build();
    }
}
