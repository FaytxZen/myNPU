package com.andrewvora.apps.mynpu.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Verifies that all of the navigational elements of the Dashboard work as expected.
 *
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class HomeNavigationTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testEditNpuOpensMapActivity() throws Exception {

    }

    @Test
    public void testSendFeedbackSendsIntent() throws Exception {

    }

    @Test
    public void testAboutNpuViewOpensAboutActivity() throws Exception {

    }

    @Test
    public void testSeeScheduleViewOpensScheduleActivity() throws Exception {

    }

    @Test
    public void testTellYourFriendsViewOpensPrompt() throws Exception {

    }
}