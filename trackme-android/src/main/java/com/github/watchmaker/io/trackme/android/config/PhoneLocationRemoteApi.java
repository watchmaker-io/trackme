package com.github.watchmaker.io.trackme.android.config;


import com.github.watchmaker.io.trackme.android.domain.PhoneLocation;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

public interface PhoneLocationRemoteApi {
    String URL = "http://localhost:8080";


    @POST("/phone-location")
    Response sendAccelerationValues(@Body PhoneLocation location);

}
