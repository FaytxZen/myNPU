package com.andrewvora.apps.mynpu.fragments;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrewvora.apps.mynpu.OurApplication;
import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.Session;
import com.andrewvora.apps.mynpu.adapters.EventsAdapter;
import com.andrewvora.apps.mynpu.listeners.DataReceiverListener;
import com.andrewvora.apps.mynpu.listeners.LocalDataReceiver;
import com.andrewvora.apps.mynpu.models.NpuData;
import com.andrewvora.apps.mynpu.utils.ServiceUtil;
import com.andrewvora.apps.mynpu.utils.ViewUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class ScheduleFragment extends BaseFragment
        implements DataReceiverListener
{
    public static final String TAG = ScheduleFragment.class.getSimpleName();

    @BindView(R.id.events) RecyclerView mEventsRecyclerView;
    @BindView(R.id.swipe_layout) SwipeRefreshLayout mSwipeLayout;

    private LocalDataReceiver mBroadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mBroadcastReceiver = new LocalDataReceiver(this);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                mBroadcastReceiver,
                new IntentFilter(OurApplication.ACTION_DATA_LOADED)
        );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, view);

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

    @Override
    public void onDataReady() {
        // try to reload the lists again
        initViews();

        // once the data is loaded there is no need to stay subscribed
        LocalBroadcastManager.getInstance(getActivity())
                .unregisterReceiver(mBroadcastReceiver);

        mBroadcastReceiver = null;
    }

    private void initViews() {
        Collection<List<NpuData>> npuDataLists = Session.getInstance().getNpuMap().values();

        if(npuDataLists.size() > 0) {
            initLists(npuDataLists);
        }

        mSwipeLayout.setColorSchemeResources(R.color.md_grey_600);
        mSwipeLayout.setRefreshing(false);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void initLists(Collection<List<NpuData>> npuDataLists) {
        List<NpuData> events = new ArrayList<>();

        for(List<NpuData> npuDataList : npuDataLists) {
            for(NpuData npuData : npuDataList) {
                npuData.setColor(ViewUtil.getRandomColor(getActivity()));
                events.add(npuData);
            }
        }

        Collections.sort(events, new Comparator<NpuData>() {
            @Override
            public int compare(NpuData lhs, NpuData rhs) {
                return lhs.getNpu().compareTo(rhs.getNpu());
            }
        });

        EventsAdapter eventsAdapter = new EventsAdapter(events);
        mEventsRecyclerView.setAdapter(eventsAdapter);

        mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventsRecyclerView.setHasFixedSize(true);
    }

    private void refreshData() {
        if(!ServiceUtil.hasInternetConnectivity(getActivity())) {
            ServiceUtil.promptNoInternet(getActivity(), R.string.text_data_is_stale);
        }
        Session.getInstance().loadMeetingData(new WeakReference<DataReceiverListener>(this));
    }
}
