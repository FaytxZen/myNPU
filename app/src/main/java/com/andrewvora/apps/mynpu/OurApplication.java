package com.andrewvora.apps.mynpu;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import com.squareup.leakcanary.LeakCanary;

/**
 * A custom Application subclass for managing app configurations.
 *
 * Created by faytxzen on 5/29/16.
 * @author faytxzen
 */
public class OurApplication extends Application implements Session.OnDataReadyListener {

    public static final String APP_PREFERENCES = "ApplicationSharedPreferences";
    public static final String ACTION_DATA_LOADED = "NpuDataHasBeenLoaded";

    @Override
    public void onCreate() {
        super.onCreate();

        // enable memory leak detection for debug builds only
        if(BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }

        // asynchronously load the npu data
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // load the map of NPU data
                loadMeetingData();
            }
        });
    }

    @Override
    public void onDataReady() {
        // NPU data retrieved, alert the app
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ACTION_DATA_LOADED));
    }

    private void loadMeetingData() {
        Session.loadMeetingData(this);
    }
}
