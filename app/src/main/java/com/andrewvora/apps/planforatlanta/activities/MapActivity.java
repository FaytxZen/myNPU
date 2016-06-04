package com.andrewvora.apps.planforatlanta.activities;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.andrewvora.apps.planforatlanta.OurApplication;
import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.dialogs.NpuDialogFragment;
import com.andrewvora.apps.planforatlanta.fragments.OurMapFragment;
import com.andrewvora.apps.planforatlanta.utils.GeoUtil;
import com.andrewvora.apps.planforatlanta.utils.ViewUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.geojson.GeoJsonFeature;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.geojson.GeoJsonPolygon;
import com.google.maps.android.ui.IconGenerator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * Created by root on 5/29/16.
 * @author flqa
 */
public class MapActivity extends AppCompatActivity implements
        OnMapReadyCallback, NpuDialogFragment.EventListener
{
    /*===========================================*
     * Constants
     *===========================================*/
    public static final String TAG_NPU = "CurrentlySetNpu";

    /*===========================================*
     * Member Variables
     *===========================================*/
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.input_address) EditText mAddressEditText;
    @BindView(R.id.text_npu) TextView mSelectedNpuTextView;
    @BindView(R.id.search_fab) FloatingActionButton mSearchFab;

    private GeoJsonLayer mNpuLayer;
    private GoogleMap mGoogleMap;
    private Marker mCurrentLocMarker;

    /*===========================================*
     * Overridden Methods
     *===========================================*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        // configure Toolbar
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_map_activity);
        }

        // configure the Map
        // get the MapFragment instance
        OurMapFragment fragment = (OurMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_fragment);

        // begin fetching the map data
        if(fragment != null) {
            fragment.getMapAsync(this);
        }

        // configure Views
        String savedNpu;
        if((savedNpu = getSharedPreferences(OurApplication.APP_PREFERENCES, MODE_PRIVATE)
                .getString(TAG_NPU, "")).isEmpty())
        {
            savedNpu = getString(R.string.title_dialog_select_npu);
        }

        mSelectedNpuTextView.setText(savedNpu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        // set up the default camera position of the map
        // should start from the ATL area
        float zoomLvl = 10.5f; // enough to see all the NPUs
        LatLng atlantaLatLng = new LatLng(33.7490, -84.3880); // the center of Atlanta
        // update camera position
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(atlantaLatLng, zoomLvl);
        googleMap.moveCamera(cameraUpdate);

        // apply the NPU layer onto the map
        try {
            mNpuLayer = new GeoJsonLayer(googleMap, R.raw.npus, getApplicationContext());
            mNpuLayer.getDefaultPolygonStyle().setStrokeWidth(2f);
            mNpuLayer.addLayerToMap();
        }
        catch (Exception ioe) {
            Log.e(MapActivity.class.getSimpleName(), ioe.getMessage());
        }

         // display a label for each NPU
        for(GeoJsonFeature feature : mNpuLayer.getFeatures()) {
            String npuLetter = feature.getProperty("NPU");
            String npu = String.format(getString(R.string.text_npu_template), npuLetter);

            IconGenerator ig = new IconGenerator(this);
            ig.setColor(ViewUtil.getRandomColor(this));
            ig.setTextAppearance(R.style.MarkerTextStyle);
            Bitmap icon = ig.makeIcon(npu);
            BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(icon);

            List<LatLng> coordinates = null;
            if(feature.hasGeometry() && feature.getGeometry().getType().equals("MultiPolygon")) {
                GeoJsonMultiPolygon polygon = (GeoJsonMultiPolygon) feature.getGeometry();
                coordinates = polygon.getPolygons().get(0).getCoordinates().get(0);
            }
            else if(feature.hasGeometry() && feature.getGeometry().getType().equals("Polygon")) {
                GeoJsonPolygon polygon = (GeoJsonPolygon) feature.getGeometry();
                coordinates = polygon.getCoordinates().get(0);
            }
            LatLng center = GeoUtil.getCentroid(coordinates);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.draggable(false).position(center).icon(descriptor);
            mGoogleMap.addMarker(markerOptions);
        }
    }

    @Override
    public void onNpuSelected(int index) {
        String npu = getResources().getStringArray(R.array.list_npus)[index];
        setNpuInPreferences(npu);

        mSelectedNpuTextView.setText(npu);
    }

    /*===========================================*
     * Event Listener Methods
     *===========================================*/
    @OnClick(R.id.text_npu)
    void onSelectedNpuClicked() {
        String[] npuArr = getResources().getStringArray(R.array.list_npus);
        String selectedNpuName = mSelectedNpuTextView.getText().toString();

        int selectedNpuIndex = getIndexOf(selectedNpuName, npuArr);

        Bundle arguments = new Bundle();
        arguments.putInt(NpuDialogFragment.TAG_SELECTED_INDEX, selectedNpuIndex);

        NpuDialogFragment selectNpuDialog = new NpuDialogFragment();
        selectNpuDialog.setArguments(arguments);
        selectNpuDialog.show(getFragmentManager(), NpuDialogFragment.TAG);
    }

    @OnClick(R.id.search_fab)
    void onSearchFabClicked() {
        // update the Views' visibilities
        toggleNpuViews();

        // hide the keyboard
        ViewUtil.hideSoftKeyboard(mAddressEditText);
    }

    @OnEditorAction(R.id.input_address)
    boolean onSearchEditorAction(TextView v) {
        if(mCurrentLocMarker != null) mCurrentLocMarker.remove();

        LatLng latLng = reverseGeocodeAddress(v.getText().toString());
        GeoJsonFeature feature = determineNPU(latLng);

        boolean hasFeature = feature != null;

        if(hasFeature) {
            // add marker to show our geocoded result
            MarkerOptions markerOptions = new MarkerOptions()
                    .title(getString(R.string.title_geocode_result_marker))
                    .draggable(false)
                    .position(latLng);
            mCurrentLocMarker = mGoogleMap.addMarker(markerOptions);

            // prompt the user if they would like to set the marker
            String template = getString(R.string.text_npu_template);
            String npu = String.format(template, feature.getProperty("NPU"));
            mSelectedNpuTextView.setText(npu);

            // save the NPU into SharedPrefs
            setNpuInPreferences(npu);
            // update the UI
            toggleNpuViews();
            // hide the keyboard
            ViewUtil.hideSoftKeyboard(mAddressEditText);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.text_dialog_cannot_find_npu)
                    .setNeutralButton(android.R.string.ok, null);
            builder.create().show();
        }

        return hasFeature;
    }

    /*===========================================*
     * Private Methods
     *===========================================*/
    private void toggleNpuViews() {
        boolean searchMode = mAddressEditText.getVisibility() == View.VISIBLE;
        int drawableRes = searchMode ?
                R.drawable.ic_search_white_24dp :
                R.drawable.ic_close_white_24dp;

        // toggle the visibility of the Views
        mSelectedNpuTextView.setVisibility(searchMode ? View.VISIBLE : View.GONE);
        mAddressEditText.setVisibility(searchMode ? View.GONE : View.VISIBLE);

        // change the FAB icon accordingly
        mSearchFab.setImageResource(drawableRes);
    }

    private void setNpuInPreferences(String npuLetter) {
        boolean saved = getSharedPreferences(OurApplication.APP_PREFERENCES, MODE_PRIVATE)
                .edit().putString(TAG_NPU, npuLetter).commit();
        if(saved) {
            String msg = String.format(getString(R.string.text_npu_set), npuLetter.toUpperCase());
            View rootView = (View) mToolbar.getParent();

            final Snackbar snackbar = Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            }).show();
        }
    }

    private LatLng reverseGeocodeAddress(String address) {
        Geocoder geocoder = new Geocoder(this);
        LatLng geocodeResult = null;

        try {
            List<Address> addresses = geocoder.getFromLocationName(address, 1);

            if(addresses.size() > 0) {
                Address firstResult = addresses.get(0);
                geocodeResult = new LatLng(firstResult.getLatitude(), firstResult.getLongitude());
            }
        }
        catch (Exception e) {
            // geocoder failed
            Log.e(MapActivity.class.getSimpleName(), e.getMessage());

            // TODO: handle the case where we fail to get a result from the Geocoder
        }

        return geocodeResult;
    }

    private GeoJsonFeature determineNPU(LatLng coord) {
        if(coord == null) return null;

        for(GeoJsonFeature feature : mNpuLayer.getFeatures()) {
            // check that the feature is a Polygon
            if(feature.hasGeometry() && feature.getGeometry().getType().equals("Polygon")) {
                GeoJsonPolygon polygon = (GeoJsonPolygon) feature.getGeometry();

                // check if the geocoded LatLng is within the feature
                boolean containsAddress = PolyUtil
                        .containsLocation(coord, polygon.getCoordinates().get(0), false);
                if(containsAddress) return feature;
            }
            else if(feature.hasGeometry() && feature.getGeometry().getType().equals("MultiPolygon"))
            {
                GeoJsonMultiPolygon multiPolygon = (GeoJsonMultiPolygon) feature.getGeometry();
                for(GeoJsonPolygon polygon : multiPolygon.getPolygons()) {
                    // check if the geocoded LatLng is within the feature
                    boolean containsAddress = PolyUtil
                            .containsLocation(coord, polygon.getCoordinates().get(0), false);
                    if(containsAddress) return feature;
                }
            }
        }

        return null;
    }

    private static <T> int getIndexOf(T item, T[] items) {
        for(int i = 0; i < items.length; i++) {
            if(items[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }
}
