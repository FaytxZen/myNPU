package com.andrewvora.apps.mynpu.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.fragments.DashboardFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The first {@link android.app.Activity} that users will see when they open the app.
 * Created by root on 5/29/16.
 * @author faytxzen
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_home_activity);
        }

        Fragment dashboardFragment = getFragmentManager().findFragmentByTag(DashboardFragment.TAG);

        if(dashboardFragment == null) {
            dashboardFragment = new DashboardFragment();
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, dashboardFragment, DashboardFragment.TAG)
                .commit();
    }
}
