package com.andrewvora.apps.mynpu;

import com.andrewvora.apps.mynpu.ui.AboutNpuTest;
import com.andrewvora.apps.mynpu.ui.HomeNavigationTest;
import com.andrewvora.apps.mynpu.ui.NpuEventScheduleTest;
import com.andrewvora.apps.mynpu.ui.NpuMapTest;
import com.andrewvora.apps.mynpu.ui.UpcomingEventsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by faytx on 7/10/2016.
 * @author faytxzen
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        HomeNavigationTest.class,
		AboutNpuTest.class,
		NpuEventScheduleTest.class,
		NpuMapTest.class,
		UpcomingEventsTest.class
})
public class NpuInstrUnitTestSuite {
}
