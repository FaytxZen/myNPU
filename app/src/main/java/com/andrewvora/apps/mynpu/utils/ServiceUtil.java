package com.andrewvora.apps.mynpu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by faytx on 8/9/2016.
 * @author faytxzen
 */
public abstract class ServiceUtil {

    /**
     * Determines whether or not there is internet access. Does not matter how we're connected,
     * i.e. via mobile data or WiFi.
     * @param context - used to retrieve the connectivity system service instance.
     * @return whether or not we have internet access.
     */
    public static boolean hasInternetConnectivity(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static void promptNoInternet(Context context, @StringRes int stringResId) {
        if(context != null) {
            Toast.makeText(context, stringResId, Toast.LENGTH_SHORT).show();
        }
    }
}
