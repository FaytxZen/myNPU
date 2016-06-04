package com.andrewvora.apps.planforatlanta;

import android.app.Application;
import android.os.Handler;

import com.squareup.leakcanary.LeakCanary;

import java.io.IOException;

/**
 * A custom Application subclass for managing app configurations.
 *
 * Created by faytxzen on 5/29/16.
 * @author faytxzen
 */
public class OurApplication extends Application {

    public static final String APP_PREFERENCES = "ApplicationSharedPreferences";

    @Override
    public void onCreate() {
        super.onCreate();

        // enable memory leak detection for debug builds only
        if(BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }

        // load the map of NPU data
        Session.loadMeetingData(getResources().openRawResource(R.raw.npu_meetings_2016));
    }
}
