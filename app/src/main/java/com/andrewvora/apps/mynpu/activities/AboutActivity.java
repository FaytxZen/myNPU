package com.andrewvora.apps.mynpu.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.fragments.AboutFragment;

/**
 * A simple display-only {@link android.app.Activity} that inflates the
 * {@link AboutFragment}
 *
 * Created by root on 6/2/16.
 * @author faytxzen
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Fragment aboutFragment = getFragmentManager().findFragmentByTag(AboutFragment.TAG);

        if(aboutFragment == null) {
            aboutFragment = new AboutFragment();
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, aboutFragment, AboutFragment.TAG)
                .commit();
    }
}
