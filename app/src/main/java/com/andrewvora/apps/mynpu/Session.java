package com.andrewvora.apps.mynpu;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import java.util.Map;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public final class Session {

    private static final String KEY_NPU_DATA = "npuMeetings";
	private static final Session mInstance = new Session();

	@Nullable private DatabaseReference databaseReference;
	@NonNull private Map<String, List<NpuData>> npuMap = new HashMap<>();

	private Session() {}

    public static Session getInstance() {
        return mInstance;
    }

    public void loadEventData(final WeakReference<DataReceiverListener> listener) {
	    if (databaseReference == null) {
		    databaseReference = FirebaseDatabase.getInstance().getReference();
	    }

        try {
            final ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                	// prevent further callbacks
	                if (databaseReference != null) {
	                	databaseReference.removeEventListener(this);
	                }

	                // reset the list of NPUs
	                npuMap.clear();

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        NpuData npuData = snapshot.getValue(NpuData.class);

                        if(npuData != null && npuData.getNpu() != null) {
                            // specify the key we're using
                            String key = npuData.getNpu().toLowerCase();

                            // initialize the List if the key doesn't exist
                            if(!npuMap.containsKey(key)) {
                                npuMap.put(key, new ArrayList<NpuData>());
                            }

                            // add the NPU event data
                            npuMap.get(key).add(npuData);
                        }
                    }

                    // alert the listener that we've loaded the data
                    if(listener.get() != null) {
                        listener.get().onDataReady();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            };

            databaseReference.child(KEY_NPU_DATA).addValueEventListener(eventListener);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Session.class.getSimpleName(), e.getMessage());
        }
    }

    @NonNull
    public Map<String, List<NpuData>> getNpuMap() {
        return mInstance.npuMap;
    }
}
