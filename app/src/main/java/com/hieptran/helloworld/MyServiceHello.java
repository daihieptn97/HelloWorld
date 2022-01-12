package com.hieptran.helloworld;

import static androidx.core.app.NotificationCompat.PRIORITY_MIN;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyServiceHello extends AccessibilityService {
    private static final String TAG = "DEBUG123";
    FrameLayout mLayout;

    public MyServiceHello() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        setNotify();
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        mLayout = new FrameLayout(this);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
//        lp.format = PixelFormat.TRANSLUCENT;
//        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.gravity = Gravity.TOP;
//        LayoutInflater inflater = LayoutInflater.from(this);
//        inflater.inflate(R.layout.action_bar, mLayout);
//        wm.addView(mLayout, lp);


//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
////                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
////                Calendar cal = Calendar.getInstance();
//
//                Log.i(TAG, "time = " + 0);
//            }
//        }, 1000, 1000);
    }


    // Clikc function that clicks at a specific x and y coordinate
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean click(float x, float y) {
        // for a single tap a duration of 1 ms is enough
        final int DURATION = 1;

        Path clickPath = new Path();
        clickPath.moveTo(x, y);
        GestureDescription.StrokeDescription clickStroke =
                new GestureDescription.StrokeDescription(clickPath, 0, DURATION);
        GestureDescription.Builder clickBuilder = new GestureDescription.Builder();
        clickBuilder.addStroke(clickStroke);
        return dispatchGesture(clickBuilder.build(), null, null);
    }


    private void setNotify() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = createNotificationChannel(notificationManager);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MIN)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();

        startForeground(1233987, notification);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(NotificationManager notificationManager) {
        String channelId = "my_service_channelid";
        String channelName = "My Foreground Service";
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        // omitted the LED color
        channel.setImportance(NotificationManager.IMPORTANCE_NONE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }
}