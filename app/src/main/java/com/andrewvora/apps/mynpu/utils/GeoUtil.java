package com.andrewvora.apps.mynpu.utils;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by root on 5/30/16.
 * @author flqa
 */
public final class GeoUtil {

    public static LatLng getCentroid(List<LatLng> points) {
        double[] centroid = { 0.0, 0.0 };

        for (int i = 0; i < points.size(); i++) {
            centroid[0] += points.get(i).latitude;
            centroid[1] += points.get(i).longitude;
        }

        int totalPoints = points.size();
        centroid[0] = centroid[0] / totalPoints;
        centroid[1] = centroid[1] / totalPoints;

        return new LatLng(centroid[0], centroid[1]);
    }
}
