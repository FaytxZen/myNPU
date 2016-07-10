package com.andrewvora.apps.mynpu.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by faytx on 7/10/2016.
 * @author faytxzen
 */
public class LocalDataReceiver extends BroadcastReceiver {

    private DataReceiverListener mListener;

    public LocalDataReceiver(DataReceiverListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(mListener != null) mListener.onDataReady();
    }

    public interface DataReceiverListener {
        void onDataReady();
    }
}
