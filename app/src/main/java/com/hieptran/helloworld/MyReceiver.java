package com.hieptran.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "hello receiver", Toast.LENGTH_SHORT).show();
        Log.d("DEBUG123", "onReceive: hello receiver hiep");

        //        context.startForegroundService(intent);

        Intent intent2 = new Intent(context, MyServiceHello.class); // Build the intent for the service
        context.startForegroundService(intent2);


    }
}
