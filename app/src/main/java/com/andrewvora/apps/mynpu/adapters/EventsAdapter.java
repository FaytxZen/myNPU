package com.andrewvora.apps.mynpu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.models.NpuData;
import com.andrewvora.apps.mynpu.utils.DateUtil;
import com.andrewvora.apps.mynpu.utils.IntentUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by faytx on 7/1/2016.
 * @author faytxzen
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    /*===========================================*
     * Member Variables
     *===========================================*/
    private List<NpuData> mEvents;

    /*===========================================*
     * Constructors
     *===========================================*/
    public EventsAdapter(List<NpuData> events) {
        mEvents = events;
    }

    /*===========================================*
     * Public Methods
     *===========================================*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NpuData event = mEvents.get(position);
        final Context context = holder.itemView.getContext();

        // set the event time
        final String eventTime = DateUtil
                .getFormattedDate(event.getTime(), event.getDay(), event.getOccurrence());
        holder.eventTimeTextView.setText(eventTime);

        // set the event NPU block
        holder.eventNpuTextView.setBackgroundColor(event.getColor());
        holder.eventNpuTextView.setText(event.getNpuName());
        holder.eventNpuTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send an Intent to a map application for the event's location String
                IntentUtil.sendMapIntent(context, IntentUtil.getGeoUriFor(event.getLocation()));
            }
        });

        // set the event title
        holder.eventTitleTextView.setText(event.getMeetingName());

        // set the event location
        holder.eventLocationTextView.setText(event.getLocation());

        // on click - send a calendar intent
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send an Intent to the ContentProvider for Calendar events
                IntentUtil.sendCalendarIntent(context, event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents != null ? mEvents.size() : 0;
    }

    /*===========================================*
     * Inner Classes
     *===========================================*/
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.event_npu) TextView eventNpuTextView;
        @BindView(R.id.event_title) TextView eventTitleTextView;
        @BindView(R.id.event_time) TextView eventTimeTextView;
        @BindView(R.id.event_location) TextView eventLocationTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
