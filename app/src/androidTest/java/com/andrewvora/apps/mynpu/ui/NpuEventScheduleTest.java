package com.andrewvora.apps.mynpu.ui;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.activities.ScheduleActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;

/**
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class NpuEventScheduleTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<ScheduleActivity> mScheduleActivityRule =
            new ActivityTestRule<>(ScheduleActivity.class);


}
