package com.andrewvora.apps.mynpu.common;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.andrewvora.apps.mynpu.R;
import com.andrewvora.apps.mynpu.activities.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by faytx on 7/10/2016.
 * @author faytxzen
 */
@RunWith(AndroidJUnit4.class)
public class BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityRule =
            new ActivityTestRule<>(HomeActivity.class);
}
