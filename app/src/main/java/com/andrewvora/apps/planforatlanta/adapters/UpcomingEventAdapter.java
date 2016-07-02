package com.andrewvora.apps.planforatlanta.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.models.NpuData;
import com.andrewvora.apps.planforatlanta.utils.DateUtil;
import com.andrewvora.apps.planforatlanta.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.ViewHolder> {

    private List<NpuData> mEvents;

    public UpcomingEventAdapter(List<NpuData> events) {
        mEvents = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_upcoming_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NpuData event = mEvents.get(position);
        final Context context = holder.itemView.getContext();

        holder.meetingIconView.setColorFilter(ViewUtil.getRandomColor(context),
                PorterDuff.Mode.SRC_ATOP);

        holder.meetingNameView.setText(event.getMeetingName());
        holder.meetingLocView.setText(event.getLocation());
        holder.meetingLocView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send maps intent
            }
        });

        String timeStr = DateUtil
                .getFormattedDate(event.getTime(), event.getDay(), event.getOccurrence());
        holder.meetingTimeView.setText(timeStr);
        holder.meetingTimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send event intent
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents != null ? mEvents.size() : 0;
    }

    public void setEvents(List<NpuData> data) {
        mEvents = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meeting_name) public TextView meetingNameView;
        @BindView(R.id.meeting_location) public TextView meetingLocView;
        @BindView(R.id.meeting_time) public TextView meetingTimeView;
        @BindView(R.id.meeting_icon) public ImageView meetingIconView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
