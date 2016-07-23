package com.andrewvora.apps.mynpu.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.models.NpuData;
import com.andrewvora.apps.mynpu.utils.DateUtil;
import com.andrewvora.apps.mynpu.utils.IntentUtil;
import com.andrewvora.apps.mynpu.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Loads the views for the events loaded in the
 * {@link com.andrewvora.apps.mynpu.fragments.DashboardFragment}
 *
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.ViewHolder> {
    /*===========================================*
     * Member Variables
     *===========================================*/
    private List<NpuData> mEvents;

    /*===========================================*
     * Constructors
     *===========================================*/
    public UpcomingEventAdapter(List<NpuData> events) {
        mEvents = events;
    }

    /*===========================================*
     * Public Methods
     *===========================================*/
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
        holder.meetingIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send an Intent to a map application for the event's location String
                IntentUtil.sendMapIntent(context, IntentUtil.getGeoUriFor(event.getLocation()));
            }
        });

        holder.meetingNameView.setText(String.format("%s - %s",
                event.getNpu(), event.getName()));
        holder.meetingLocView.setText(event.getLocation());

        String timeStr = DateUtil
                .getFormattedDate(event.getTime(), event.getDay(), event.getOccurrence());
        holder.meetingTimeView.setText(timeStr);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send an Intent to the Calendar ContentProvider to add this event
                IntentUtil.sendCalendarIntent(context, event);
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

    public List<NpuData> getEvents() {
        return mEvents;
    }

    /*===========================================*
     * Inner Classes
     *===========================================*/
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
