package com.andrewvora.apps.planforatlanta.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.Session;
import com.andrewvora.apps.planforatlanta.activities.AboutActivity;
import com.andrewvora.apps.planforatlanta.activities.MapActivity;
import com.andrewvora.apps.planforatlanta.activities.ScheduleActivity;
import com.andrewvora.apps.planforatlanta.adapters.UpcomingEventAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 5/31/16.
 * @author faytxzen
 */
public class DashboardFragment extends BaseFragment {

    public static final String TAG = DashboardFragment.class.getSimpleName();

    @BindView(R.id.list_upcoming_events) RecyclerView mEventsRecyclerView;
    @BindView(R.id.text_selected_npu) TextView mNpuTextView;

    private UpcomingEventAdapter mEventAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, parent, false);
        ButterKnife.bind(this, view);

        setNpu();
        initViews();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setNpu();

        String key = getNpuFromPref().toLowerCase();
        mEventAdapter.setEvents(Session.getNpuMap().get(key));
        mEventAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.set_npu_fab)
    void onFindNpuClicked() {
        Intent findNpuIntent = new Intent(getActivity(), MapActivity.class);
        startActivityForResult(findNpuIntent, 0);
    }

    @OnClick(R.id.npu_meeting_times)
    void onNpuMeetingTimesClicked() {
        Intent showAllMeetingTimesIntent = new Intent(getActivity(), ScheduleActivity.class);
        startActivity(showAllMeetingTimesIntent);
    }

    @OnClick(R.id.about_npus)
    void onAboutNpusClicked() {
        Intent openAboutActivityIntent = new Intent(getActivity(), AboutActivity.class);
        startActivity(openAboutActivityIntent);
    }

    @OnClick(R.id.share_this_app)
    void onShareClicked() {
        Intent shareIntent = new Intent();
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_default_share));
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }

    private void initViews() {
        String key = getNpuFromPref().toLowerCase();
        mEventAdapter = new UpcomingEventAdapter(Session.getNpuMap().get(key));

        mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventsRecyclerView.setHasFixedSize(true);
        mEventsRecyclerView.setAdapter(mEventAdapter);
    }

    private void setNpu() {
        mNpuTextView.setText(getNpuFromPref());
    }
}
