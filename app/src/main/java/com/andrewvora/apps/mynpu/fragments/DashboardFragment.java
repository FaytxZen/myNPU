package com.andrewvora.apps.mynpu.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrewvora.apps.mynpu.OurApplication;
import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.Session;
import com.andrewvora.apps.mynpu.activities.AboutActivity;
import com.andrewvora.apps.mynpu.activities.MapActivity;
import com.andrewvora.apps.mynpu.activities.ScheduleActivity;
import com.andrewvora.apps.mynpu.adapters.UpcomingEventAdapter;
import com.andrewvora.apps.mynpu.listeners.LocalDataReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Handles the display of information important to the user at launch:
 *  - their NPU and  NPU events (regular user)
 *  - a way to access the entire NPU meeting schedule (regular user)
 *  - a description of what NPUs are (new user)
 *
 * Created by root on 5/31/16.
 * @author faytxzen
 */
public class DashboardFragment extends BaseFragment
        implements LocalDataReceiver.DataReceiverListener
{
    /*===========================================*
     * Constants
     *===========================================*/
    public static final String TAG = DashboardFragment.class.getSimpleName();

    /*===========================================*
     * Member Variables
     *===========================================*/
    @BindView(R.id.list_upcoming_events) RecyclerView mEventsRecyclerView;
    @BindView(R.id.text_selected_npu) TextView mNpuTextView;
    @BindView(R.id.empty_events_view) TextView mEmptyTextView;

    private UpcomingEventAdapter mEventAdapter;
    private LocalDataReceiver mBroadcastReceiver;

    /*===========================================*
     * Lifecycle Methods
     *===========================================*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mBroadcastReceiver = new LocalDataReceiver(this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                        mBroadcastReceiver,
                        new IntentFilter(OurApplication.ACTION_DATA_LOADED));
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
    public void onPause() {

        if(mBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity())
                    .unregisterReceiver(mBroadcastReceiver);
        }

        super.onPause();
    }

    /*===========================================*
     * Overridden Methods
     *===========================================*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // update the NPU text
        setNpu();

        // re-initialize the events adapter to use the most current NPU
        refreshEventsAdapter();

        // re-determine if we should show the empty events views
        updateEmptyEventsViews();
    }

    @Override
    public void onDataReady() {
        // refresh the list views
        refreshEventsAdapter();
        updateEmptyEventsViews();

        // once the data is loaded there is no need to stay subscribed
        LocalBroadcastManager.getInstance(getActivity())
                .unregisterReceiver(mBroadcastReceiver);

        mBroadcastReceiver = null;
    }

    /*===========================================*
     * Event Listeners
     *===========================================*/
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
        // send a basic text Intent to all the apps
        Intent shareIntent = new Intent();
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_default_share));
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }

    /*===========================================*
     * Private Methods
     *===========================================*/
    private void initViews() {
        // initialize the adapter based on the set NPU
        String key = getNpuFromPref().toLowerCase();
        mEventAdapter = new UpcomingEventAdapter(Session.getNpuMap().get(key));

        // initialize the RecyclerView
        mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventsRecyclerView.setHasFixedSize(true);
        // prevent capture of regular scroll events
        mEventsRecyclerView.setNestedScrollingEnabled(false);
        mEventsRecyclerView.setAdapter(mEventAdapter);

        updateEmptyEventsViews();
    }

    private void refreshEventsAdapter() {
        // reload the events based on the newly set NPU or data source
        String key = getNpuFromPref().toLowerCase();
        mEventAdapter.setEvents(Session.getNpuMap().get(key));
        mEventAdapter.notifyDataSetChanged();
    }

    private void updateEmptyEventsViews() {
        // null-check
        if(mEventAdapter == null || mEmptyTextView == null) return;

        // determine which visibility to use
        int emptyEventsMessage = mEventAdapter.getItemCount() == 0 ?
                View.VISIBLE : View.GONE;

        // apply it
        mEmptyTextView.setVisibility(emptyEventsMessage);
    }

    private void setNpu() {
        mNpuTextView.setText(getNpuFromPref());
    }
}
