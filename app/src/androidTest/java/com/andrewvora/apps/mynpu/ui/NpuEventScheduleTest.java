package com.andrewvora.apps.mynpu.ui;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.activities.ScheduleActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.andrewvora.apps.mynpu.common.viewactions.ClickChildViewWithId.clickViewWithId;

/**
 * Tests for the {@link ScheduleActivity}.
 *
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class NpuEventScheduleTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<HomeActivity> mScheduleActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Override
    public void setup() {
        super.setup();

        onView(withId(R.id.npu_meeting_times)).perform(scrollTo()).perform(click());
    }

    @Test
    public void testClickingOnAnEventItemDetails() {
        // make sure clicking on an item doesn't cause a crash
        // click on the event details
        onView(withId(R.id.events)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testClickingOnAnEventIconForMap() {
        // make sure clicking on an item doesn't cause  a crash
        // click on the event details
        onView(withId(R.id.events)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickViewWithId(R.id.event_npu)));
    }
}
