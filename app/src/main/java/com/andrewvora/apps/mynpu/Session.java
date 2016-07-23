package com.andrewvora.apps.mynpu;

import android.util.Log;

import com.andrewvora.apps.mynpu.models.NpuData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public final class Session {

    private static final String KEY_NPU_DATA = "npuMeetings";

    public HashMap<String, List<NpuData>> npuMap = new HashMap<>();
    private static final Session mInstance = new Session();
    private Session() {}

    public interface OnDataReadyListener {
        void onDataReady();
    }

    public static boolean loadMeetingData(final OnDataReadyListener listener) {

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
                    listener.onDataReady();
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

    public static HashMap<String, List<NpuData>> getNpuMap() {
        return mInstance.npuMap;
    }

    private static void setNpuMap(String json) {
        try {
            JSONObject npuJson = new JSONObject(json);
            JSONArray npuJsonArr = npuJson.getJSONArray("npuMeetings");

            for(int i = 0; i < npuJsonArr.length(); i++) {
                JSONObject obj = npuJsonArr.getJSONObject(i);
                String npuName = obj.getString("npu");
                String key = npuName.toLowerCase();

                if(!getNpuMap().containsKey(key)) {
                    getNpuMap().put(key, new ArrayList<NpuData>());
                }

                NpuData npuData = new NpuData();
                npuData.setNpu(npuName);
                npuData.setName(obj.getString("name"));
                npuData.setLocation(obj.getString("location"));
                npuData.setDay(obj.getString("day"));
                npuData.setOccurrence(obj.getInt("occurrence"));
                npuData.setTime(obj.getString("time"));
                npuData.setFrequency(obj.getString("frequency"));

                getNpuMap().get(key).add(npuData);
            }
        }
        catch (JSONException je) {
            je.printStackTrace();
            Log.e(Session.class.getSimpleName(), je.getMessage());
        }
    }
}
