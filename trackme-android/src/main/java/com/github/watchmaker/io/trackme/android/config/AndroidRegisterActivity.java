package com.github.watchmaker.io.trackme.android.config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AndroidRegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent serviceIntent = new Intent(this, AndroidPhoneLocationCollectingService.class);
        startService(serviceIntent);

    }
}
