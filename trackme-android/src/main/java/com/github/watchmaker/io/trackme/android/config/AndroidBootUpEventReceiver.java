package com.github.watchmaker.io.trackme.android.config;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AndroidBootUpEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent tracePhoneIntent = new Intent(context, AndroidPhoneLocationCollectingService.class);
        context.startService(tracePhoneIntent);
    }

}