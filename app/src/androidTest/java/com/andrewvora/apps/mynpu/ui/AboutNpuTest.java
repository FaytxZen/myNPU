package com.andrewvora.apps.mynpu.ui;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.activities.AboutActivity;
import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;

/**
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class AboutNpuTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<AboutActivity> mAboutActivityRule =
            new ActivityTestRule<>(AboutActivity.class);


}
