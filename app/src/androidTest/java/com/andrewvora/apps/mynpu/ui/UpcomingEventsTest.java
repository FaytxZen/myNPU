package com.andrewvora.apps.mynpu.ui;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;
import com.andrewvora.apps.mynpu.fragments.DashboardFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.andrewvora.apps.mynpu.common.viewactions.ClickChildViewWithId.clickViewWithId;

/**
 * Tests for the list of upcoming events on {@link DashboardFragment}
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class UpcomingEventsTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testClickingOnEventDetailsWillNotCrashApp() throws Exception {
        onView(withId(R.id.list_upcoming_events)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click())
        );
    }

    @Test
    public void testClickingOnEventMarkerWillNotCrashApp() throws Exception {
        onView(withId(R.id.list_upcoming_events)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickViewWithId(R.id.meeting_icon))
        );
    }
}
