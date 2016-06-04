package com.andrewvora.apps.planforatlanta;

import android.util.Log;

import com.andrewvora.apps.planforatlanta.models.NpuData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public final class Session {

    public HashMap<String, List<NpuData>> npuMap = new HashMap<>();
    private static final Session mInstance = new Session();
    private Session() {}

    public static boolean loadMeetingData(InputStream is) {
        String meetingJson;

        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            int numBytesRead = is.read(buffer);
            is.close();

            if(numBytesRead != size) return false;
            else {
                meetingJson = new String(buffer, "UTF-8");
                setNpuMap(meetingJson);
            }
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
                npuData.setNpuName(npuName);
                npuData.setMeetingName(obj.getString("name"));
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
