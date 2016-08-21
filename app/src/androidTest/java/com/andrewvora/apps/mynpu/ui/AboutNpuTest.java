package com.andrewvora.apps.mynpu.ui;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.andrewvora.apps.mynpu.common.viewactions.NestedScrollToAction.betterScrollTo;

/**
 * Tests the UI functionality of {@link com.andrewvora.apps.mynpu.fragments.AboutFragment}
 *
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class AboutNpuTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<HomeActivity> mAboutActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Override
    public void setup() {
        super.setup();

        onView(withId(R.id.about_npus)).perform(scrollTo()).perform(click());
    }

    @Test
    public void testScrolling() throws Exception {
        // try to scroll
        onView(withId(R.id.collapsing_toolbar_layout)).perform(swipeUp());
    }

    @Test
    public void testExternalLinksDoNotCrashApp() throws Exception {
        // make sure all controls linking to external sources are visible
        onView(withId(R.id.go_to_atl_site_fab)).check(matches(isDisplayed()));

        // click on the controls to make sure they still work
        onView(withId(R.id.go_to_atl_site_fab)).perform(click());
    }

    @Test
    public void testHasLinkToMapActivity() throws Exception {
        // collapse the toolbar
        onView(withId(R.id.collapsing_toolbar_layout)).perform(swipeUp());

        // scroll to the button
        onView(withId(R.id.find_my_npu_button)).perform(betterScrollTo());

        // click on it
        onView(withId(R.id.find_my_npu_button)).perform(click());

        // make sure we're in the map activity
        onView(withId(R.id.search_fab)).check(matches(isDisplayed()));
    }
}
