package com.andrewvora.apps.planforatlanta;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * A custom Application subclass for managing app configurations.
 *
 * Created by faytxzen on 5/29/16.
 * @author faytxzen
 */
public class OurApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // enable memory leak detection for debug builds only
        if(BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
