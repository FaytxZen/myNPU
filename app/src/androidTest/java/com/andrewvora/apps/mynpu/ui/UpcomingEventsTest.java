package com.andrewvora.apps.mynpu.ui;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class UpcomingEventsTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testClickingOnEventDetailsSendCalendarIntent() throws Exception {

    }

    @Test
    public void testClickingOnEventMarkerSendsMapIntent() throws Exception {

    }
}
