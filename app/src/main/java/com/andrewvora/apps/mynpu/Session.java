package com.andrewvora.apps.mynpu;

import android.util.Log;

import com.andrewvora.apps.mynpu.listeners.DataReceiverListener;
import com.andrewvora.apps.mynpu.models.NpuData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public final class Session {

    private static final String KEY_NPU_DATA = "npuMeetings";

    public HashMap<String, List<NpuData>> npuMap;
    private static final Session mInstance = new Session();
    private Session() {}

    public static Session getInstance() {
        return mInstance;
    }

    public boolean loadMeetingData
            (final WeakReference<DataReceiverListener> listener)
    {
        setNpuMap(new HashMap<String, List<NpuData>>());

        try {
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        NpuData npuData = snapshot.getValue(NpuData.class);

                        if(npuData != null && npuData.getNpu() != null) {
                            // specify the key we're using
                            String key = npuData.getNpu().toLowerCase();

                            // initialize the List if the key doesn't exist
                            if(!getNpuMap().containsKey(key)) {
                                getNpuMap().put(key, new ArrayList<NpuData>());
                            }

                            // add the NPU event data
                            getNpuMap().get(key).add(npuData);
                        }
                    }

                    // alert the listener that we've loaded the data
                    if(listener.get() != null) {
                        listener.get().onDataReady();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) { }
            };

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(KEY_NPU_DATA).addValueEventListener(eventListener);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Session.class.getSimpleName(), e.getMessage());
            return false;
        }

        return true;
    }

    public HashMap<String, List<NpuData>> getNpuMap() {
        return mInstance.npuMap;
    }

    public void setNpuMap(HashMap<String, List<NpuData>> map) {
        mInstance.npuMap = map;
    }
}
