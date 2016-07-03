package com.github.watchmaker.io.trackme.service.phones.api;


public abstract class AbstractPhoneEndpoint {
    private String requiredAccessToken;


    public AbstractPhoneEndpoint(String requiredAccessToken) {
        this.requiredAccessToken = requiredAccessToken;
    }

    protected void checkAccessToken(String accessToken) {
        if (!requiredAccessToken.equals(accessToken)){
            throw new IllegalArgumentException(String.format("Incorrect access token: %s", accessToken));
        }
    }
}
