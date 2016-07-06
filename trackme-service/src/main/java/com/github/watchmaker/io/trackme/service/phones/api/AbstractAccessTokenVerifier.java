package com.github.watchmaker.io.trackme.service.phones.api;


public abstract class AbstractAccessTokenVerifier {
    private String requiredAccessToken;


    public AbstractAccessTokenVerifier(String requiredAccessToken) {
        this.requiredAccessToken = requiredAccessToken;
    }

    protected void verifyAccessToken(String accessToken) {
        if (!requiredAccessToken.equals(accessToken)){
            throw new IllegalArgumentException(String.format("Incorrect access token: %s", accessToken));
        }
    }
}
