package com.hieptran.helloworld;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "hello receiver", Toast.LENGTH_SHORT).show();
        Log.d("DEBUG123", "onReceive: hello receiver hiep");

        //        context.startForegroundService(intent);

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "DEBUG_HELLO_WORLD");
        wl.acquire();

        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("vn.greenapp.worktrack");
        if (launchIntent != null) {
            context.startActivity(launchIntent);//null pointer check in case package name was not found
        }

        Intent intent2 = new Intent(context, MyServiceHello.class); // Build the intent for the service
        context.startForegroundService(intent2);
    }


}
