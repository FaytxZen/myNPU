package com.andrewvora.apps.planforatlanta.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.models.NpuData;
import com.andrewvora.apps.planforatlanta.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<NpuData> mEvents;

    public EventAdapter(List<NpuData> events) {
        mEvents = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NpuData event = mEvents.get(position);

        holder.meetingNameView.setText(event.getMeetingName());
        holder.meetingLocView.setText(event.getLocation());

        holder.meetingTimeView.setText(
                DateUtil.getFormattedDate(event.getTime(), event.getDay(), event.getOccurrence()));
    }

    @Override
    public int getItemCount() {
        return mEvents != null ? mEvents.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meeting_name) public TextView meetingNameView;
        @BindView(R.id.meeting_location) public TextView meetingLocView;
        @BindView(R.id.meeting_time) public TextView meetingTimeView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
