package com.andrewvora.apps.planforatlanta.fragments;

import android.app.Fragment;
import android.content.Context;

import com.andrewvora.apps.planforatlanta.OurApplication;
import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.activities.MapActivity;

/**
 * Created by faytx on 7/1/2016.
 * @author faytxzen
 */
public class BaseFragment extends Fragment {

    protected String getNpuFromPref() {
        String defaultNpuText = getString(R.string.text_default_npu);

        return getActivity()
                .getSharedPreferences(OurApplication.APP_PREFERENCES, Context.MODE_PRIVATE)
                .getString(MapActivity.TAG_NPU, defaultNpuText);
    }
}
