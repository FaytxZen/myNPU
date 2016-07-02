package com.andrewvora.apps.planforatlanta.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrewvora.apps.planforatlanta.R;
import com.andrewvora.apps.planforatlanta.models.NpuData;
import com.andrewvora.apps.planforatlanta.utils.DateUtil;
import com.andrewvora.apps.planforatlanta.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by faytx on 7/1/2016.
 * @author faytxzen
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<NpuData> mEvents;

    public EventsAdapter(List<NpuData> events) {
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

        // set the event time
        final String eventTime = DateUtil
                .getFormattedDate(event.getTime(), event.getDay(), event.getOccurrence());
        holder.eventTimeTextView.setText(eventTime);
        holder.eventTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send event intent
            }
        });

        // set the event NPU block
        holder.eventNpuTextView.setBackgroundColor(event.getColor());
        holder.eventNpuTextView.setText(event.getNpuName());

        // set the event title
        holder.eventTitleTextView.setText(event.getMeetingName());

        // set the event location
        holder.eventLocationTextView.setText(event.getLocation());
        holder.eventLocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send maps intent
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents != null ? mEvents.size() : 0;
    }

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
