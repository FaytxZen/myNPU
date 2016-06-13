package com.andrewvora.apps.planforatlanta.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.fragments.AboutFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
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
