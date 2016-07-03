package com.github.watchmaker.io.trackme.service.phones.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<PhoneLocation> findUserPhoneLocation(UUID userId) {
        return phoneLocationRepository.findByUserId(userId);
    }
}
