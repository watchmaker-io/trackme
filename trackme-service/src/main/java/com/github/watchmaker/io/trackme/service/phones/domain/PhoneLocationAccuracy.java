package com.github.watchmaker.io.trackme.service.phones.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum PhoneLocationAccuracy {
    GPS,
    NETWORK_PROVIDER;

    private static final Map<String, PhoneLocationAccuracy> byName = new HashMap<>();

    static {
        for (PhoneLocationAccuracy offerType : PhoneLocationAccuracy.values()) {
            byName.put(offerType.name(), offerType);
        }
    }
    public static PhoneLocationAccuracy from(String value) {
        return Optional.ofNullable(byName.get(value))
                        .orElseThrow(() -> new IllegalArgumentException("Invalid phoneLocationAccuracy name value: "
                                + value));
    }
}
