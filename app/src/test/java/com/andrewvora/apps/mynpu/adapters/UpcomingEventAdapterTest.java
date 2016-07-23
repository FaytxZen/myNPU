package com.andrewvora.apps.mynpu.adapters;

import com.andrewvora.apps.mynpu.models.NpuData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by faytx on 7/23/2016.
 * @author faytxzen
 */
public class UpcomingEventAdapterTest {

    @Test
    public void testGetItemCount() throws Exception {
        // TEST: null case
        UpcomingEventAdapter eventsAdapter = new UpcomingEventAdapter(null);
        assertEquals(0, eventsAdapter.getItemCount());

        // TEST: empty case
        eventsAdapter = new UpcomingEventAdapter(new ArrayList<NpuData>());
        assertEquals(0, eventsAdapter.getItemCount());

        // TEST: normal case
        List<NpuData> events = new ArrayList<>();
        events.add(new NpuData());
        events.add(new NpuData());

        eventsAdapter = new UpcomingEventAdapter(events);

        assertEquals(events.size(), eventsAdapter.getItemCount());
    }

    @Test
    public void testSetEvents() throws Exception {
        // TEST: null case
        UpcomingEventAdapter adapter = new UpcomingEventAdapter(null);
        assertEquals(null, adapter.getEvents());

        // TEST: regular case
        List<NpuData> events = new ArrayList<>();
        adapter.setEvents(events);
        assertEquals(events, adapter.getEvents());
    }
}