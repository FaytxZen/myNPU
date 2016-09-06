package com.andrewvora.apps.mynpu.ui;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.andrewvora.apps.mynpu.activities.HomeActivity;
import com.andrewvora.apps.mynpu.activities.MapActivity;
import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link MapActivity}.
 *
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
@LargeTest
public class NpuMapTest extends BaseInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<MapActivity> mMapActivityRule =
            new ActivityTestRule<>(MapActivity.class);

    @Test
    public void testClickingSearchFabTogglesInputField() {
        
    }

    @Test
    public void testSettingNpuFromSpinner() {

    }

    @Test
    public void testQueryingForTypedAddress() {

    }
}
