package com.github.watchmaker.io.trackme.android.config;

import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import com.github.watchmaker.io.trackme.android.domain.PhoneLocation;
import com.github.watchmaker.io.trackme.android.domain.PhoneLocationAccuracy;
import org.joda.time.DateTime;
import retrofit.RestAdapter;

public class AndroidPhoneLocationListener implements LocationListener {

    private PhoneLocationRemoteApi phoneLocationRemoteApi;
    private PhoneLocationAccuracy phoneLocationAccuracy;

    // FIXME dchojnacki
    private String userId = "123456-abcd-efgh-7890";


    public AndroidPhoneLocationListener(PhoneLocationAccuracy phoneLocationAccuracy) {
        this.phoneLocationAccuracy = phoneLocationAccuracy;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(PhoneLocationRemoteApi.URL)
                .build();

        this.phoneLocationRemoteApi = restAdapter.create(PhoneLocationRemoteApi.class);
    }

    @Override
    public void onLocationChanged(Location location) {
        PhoneLocation phoneLocation = new PhoneLocation(
                     location.getLongitude(),
                     location.getLatitude(),
                     location.getAltitude(),
                     location.getSpeed(),
                     phoneLocationAccuracy,
                     new DateTime(location.getTime()));

        System.err.println(phoneLocation);

//        new SendLocationAsyncTask().execute(phoneLocation);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // nothing to do here
    }

    @Override
    public void onProviderEnabled(String s) {
        // nothing to do here
    }

    @Override
    public void onProviderDisabled(String s) {
        // nothing to do here
    }


    private class SendLocationAsyncTask extends AsyncTask<PhoneLocation, Void, Void> {

        @Override
        protected Void doInBackground(PhoneLocation... params) {
            try {
                phoneLocationRemoteApi.sendAccelerationValues(userId, params[0]);
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
