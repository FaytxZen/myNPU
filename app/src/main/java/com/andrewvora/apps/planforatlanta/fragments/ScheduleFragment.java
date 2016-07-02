package com.andrewvora.apps.planforatlanta.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.Session;
import com.andrewvora.apps.planforatlanta.adapters.EventsAdapter;
import com.andrewvora.apps.planforatlanta.models.NpuData;
import com.andrewvora.apps.planforatlanta.utils.ViewUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class ScheduleFragment extends BaseFragment {

    public static final String TAG = ScheduleFragment.class.getSimpleName();

    @BindView(R.id.events) RecyclerView mEventsRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, view);

        initViews();

        return view;
    }

    private void initViews() {

        List<NpuData> events = new ArrayList<>();
        Collection<List<NpuData>> npuDataLists = Session.getNpuMap().values();
        for(List<NpuData> npuDataList : npuDataLists) {
            for(NpuData npuData : npuDataList) {
                npuData.setColor(ViewUtil.getRandomColor(getActivity()));
                events.add(npuData);
            }
        }

        Collections.sort(events, new Comparator<NpuData>() {
            @Override
            public int compare(NpuData lhs, NpuData rhs) {
                return lhs.getNpuName().compareTo(rhs.getNpuName());
            }
        });

        EventsAdapter eventsAdapter = new EventsAdapter(events);
        mEventsRecyclerView.setAdapter(eventsAdapter);

        mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEventsRecyclerView.setHasFixedSize(true);
    }
}
