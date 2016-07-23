package com.andrewvora.apps.mynpu;

import com.andrewvora.apps.mynpu.models.NpuDataTest;
import com.andrewvora.apps.mynpu.adapters.EventsAdapterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by faytx on 7/10/2016.
 * @author faytxzen
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventsAdapterTest.class,
        NpuDataTest.class
})
public class NpuUnitTestSuite { }
