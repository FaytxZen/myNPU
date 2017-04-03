package com.andrewvora.apps.mynpu.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Verifies that all of the navigational elements of the Dashboard work as expected.
 *
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
public class HomeNavigationTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testEditNpuOpensMapActivity() throws Exception {
        // attempt to open the map activity
        onView(withId(R.id.set_npu_fab)).perform(click());

        // verify we're on that activity
        onView(withId(R.id.search_fab)).check(matches(isDisplayed()));
    }

    @Test
    public void testAboutNpuViewOpensAboutActivity() throws Exception {
        // attempt to open the about activity
        onView(withId(R.id.about_npus)).perform(scrollTo()).perform(click());

        // verify we're on that activity
        onView(withId(R.id.go_to_atl_site_fab)).check(matches(isDisplayed()));
    }

    @Test
    public void testSeeScheduleViewOpensScheduleActivity() throws Exception {
        // attempt to open the schedule activity
        onView(withId(R.id.npu_meeting_times)).perform(scrollTo()).perform(click());

        // verify we're on that activity
        onView(withId(R.id.events)).check(matches(isDisplayed()));
    }

    @Test
    public void testTellYourFriendsDoesNotThrowException() throws Exception {
        // attempt to open the dialog
        onView(withId(R.id.share_this_app)).perform(scrollTo()).check(matches(isDisplayed()));
    }
}