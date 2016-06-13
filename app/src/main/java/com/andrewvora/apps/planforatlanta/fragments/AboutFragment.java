package com.andrewvora.apps.planforatlanta.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.activities.MapActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class AboutFragment extends Fragment {

    public static final String TAG = AboutFragment.class.getSimpleName();

    @BindView(R.id.collapsing_toolbar_layout) CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);

        mToolbarLayout.setTitle(getString(R.string.title_about_activity));

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);

        if(activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }

    @OnClick(R.id.go_to_atl_site_fab)
    void onGoToAtlSiteClicked() {
        Uri atlSite = Uri.parse(getString(R.string.text_about_source_url));
        Intent openAtlSiteIntent = new Intent(Intent.ACTION_VIEW, atlSite);

        if(openAtlSiteIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(openAtlSiteIntent);
        }
    }

    @OnClick(R.id.find_my_npu_button)
    void onFindMyNpuClicked() {
        startActivity(new Intent(getActivity(), MapActivity.class));
    }
}
