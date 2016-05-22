package com.github.watchmaker.io.trackme.android.config;

import android.app.IntentService;
import android.content.Intent;
import android.location.LocationManager;
import com.github.watchmaker.io.trackme.android.domain.PhoneLocationAccuracy;

public class AndroidPhoneLocationCollectingService extends IntentService {

    public AndroidPhoneLocationCollectingService() {
        super("com.github.watchmaker.io.trackme.android.config.AndroidPhoneLocationCollectingService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5 * 60 * 1000, 100,
//                new AndroidPhoneLocationListener());
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15 * 60 * 1000, 250,
//                new AndroidPhoneLocationListener());
        // FIXME dchojnacki
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                    new AndroidPhoneLocationListener(PhoneLocationAccuracy.NETWORK_PROVIDER));
        }
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                    new AndroidPhoneLocationListener(PhoneLocationAccuracy.GPS));
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // nothing to do here
    }
}
