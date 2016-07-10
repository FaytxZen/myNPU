package com.andrewvora.apps.mynpu.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.fragments.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A wrapping {@link android.app.Activity} for a {@link android.preference.PreferenceFragment}
 * subclass.
 *
 * Currently, not being used.
 *
 * Created by root on 6/6/16.
 * @author faytxzen
 */
public class SettingsActivity extends BaseActivity
{
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUseDefaultOptionsMenu(false);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.menu_settings);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Fragment settingsFragment = getFragmentManager().findFragmentByTag(SettingsFragment.TAG);

        if(settingsFragment == null) {
            settingsFragment = new SettingsFragment();
        }

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, settingsFragment, SettingsFragment.TAG)
                .commit();
    }
}
