package com.andrewvora.apps.mynpu;

import com.andrewvora.apps.mynpu.adapters.NpuAdapterTest;
import com.andrewvora.apps.mynpu.adapters.UpcomingEventAdapter;
import com.andrewvora.apps.mynpu.adapters.UpcomingEventAdapterTest;
import com.andrewvora.apps.mynpu.models.NpuDataTest;
import com.andrewvora.apps.mynpu.adapters.EventsAdapterTest;
import com.andrewvora.apps.mynpu.models.SessionTest;
import com.andrewvora.apps.mynpu.utils.DateUtilTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by faytx on 7/10/2016.
 * @author faytxzen
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventsAdapterTest.class,
		NpuAdapterTest.class,
		UpcomingEventAdapterTest.class,
		SessionTest.class,
        NpuDataTest.class,
		DateUtilTest.class
})
public class NpuUnitTestSuite { }
