package com.andrewvora.apps.planforatlanta;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import butterknife.ButterKnife;

/**
 * Created by root on 5/29/16.
 * @author faytxzen
 */
public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        HomeMapFragment fragment = (HomeMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_fragment);

        if(fragment != null) {
            fragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }
}
