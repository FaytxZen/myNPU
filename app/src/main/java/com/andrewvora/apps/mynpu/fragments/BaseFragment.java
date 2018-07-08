package com.andrewvora.apps.mynpu.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andrewvora.apps.mynpu.OurApplication;
import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.activities.MapActivity;

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

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
