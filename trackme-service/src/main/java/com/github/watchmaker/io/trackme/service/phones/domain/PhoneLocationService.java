package com.github.watchmaker.io.trackme.service.phones.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhoneLocationService {
    private PhoneLocationRepository phoneLocationRepository;


    @Autowired
    public PhoneLocationService(PhoneLocationRepository phoneLocationRepository) {
        this.phoneLocationRepository = phoneLocationRepository;
    }

    public void insert(PhoneLocation phoneLocation) {
        phoneLocationRepository.save(phoneLocation);
    }

    public PhoneLocation findUserPhoneLastLocation(UUID userId) {
        return phoneLocationRepository.findLastLocationByUserId(userId);
    }
}
