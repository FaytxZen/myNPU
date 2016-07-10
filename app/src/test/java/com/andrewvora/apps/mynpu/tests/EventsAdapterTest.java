package com.andrewvora.apps.mynpu.tests;

import com.andrewvora.apps.mynpu.adapters.EventsAdapter;
import com.andrewvora.apps.mynpu.common.BaseUnitTest;
import com.andrewvora.apps.mynpu.models.NpuData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link EventsAdapter}
 *
 * Created by faytx on 7/10/2016.
 * @author faytxzen
 */
public class EventsAdapterTest extends BaseUnitTest {

    /**
     * Unit test for {@link EventsAdapter#getItemCount()}
     * @throws Exception
     */
    @Test
    public void testGetItemCount() throws Exception {
        // TEST: null case
        EventsAdapter eventsAdapter = new EventsAdapter(null);
        assertEquals(0, eventsAdapter.getItemCount());

        // TEST: empty case
        eventsAdapter = new EventsAdapter(new ArrayList<NpuData>());
        assertEquals(0, eventsAdapter.getItemCount());

        // TEST: normal case
        List<NpuData> events = new ArrayList<>();
        events.add(new NpuData());
        events.add(new NpuData());

        eventsAdapter = new EventsAdapter(events);

        assertEquals(events.size(), eventsAdapter.getItemCount());
    }
}