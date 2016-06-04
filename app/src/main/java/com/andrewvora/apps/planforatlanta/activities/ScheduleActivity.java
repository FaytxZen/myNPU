package com.andrewvora.apps.planforatlanta.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.andrewvora.apps.planforatlanta.R;

import butterknife.BindView;

/**
 * Created by root on 6/2/16.
 * @author faytxzen
 */
public class ScheduleActivity extends BaseActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setSupportActionBar(mToolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
