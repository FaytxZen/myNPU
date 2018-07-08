package com.andrewvora.apps.mynpu.models;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Tests methods in {@link NpuData}.
 *
 * Created by faytx on 7/17/2016.
 * @author faytxzen
 */
public class NpuDataTest {

    /**
     * {@link NpuData#getStartTime()}
     */
    @Test
    public void testGetStartTime() {
        // set up
        NpuData data = new NpuData();

        // TEST: initial state returns 0 seconds
        assertEquals(0L, data.getStartTime());

        // TEST: incorrect time
        data.setTime("30:00 PM");
        assertEquals(0L, data.getStartTime());

        // TEST: correct time - morning - returns next meeting date at the set time
        data.setTime("6:15 AM");
        data.setDay("Tuesday");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfWeek = Calendar.TUESDAY;

        calendar.setTimeInMillis(data.getStartTime());
        assertEquals(year, calendar.get(Calendar.YEAR));
        assertTrue(Math.abs(month - calendar.get(Calendar.MONTH)) <= 1);
        assertEquals(dayOfWeek, calendar.get(Calendar.DAY_OF_WEEK));
        assertEquals(6, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(15, calendar.get(Calendar.MINUTE));

        // TEST: correct time - evening - returns today at the set time
        data.setTime("6:45 PM");
        data.setDay("Friday");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfWeek = Calendar.FRIDAY;

        calendar.setTimeInMillis(data.getStartTime());
        assertEquals(year, calendar.get(Calendar.YEAR));
        assertTrue(Math.abs(month - calendar.get(Calendar.MONTH)) <= 1);
        assertEquals(dayOfWeek, calendar.get(Calendar.DAY_OF_WEEK));
        assertEquals(18, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(45, calendar.get(Calendar.MINUTE));
    }

    /**
     * {@link NpuData#getEndTime()}
     */
    @Test
    public void testGetEndTime() {
        // set up
        NpuData data = new NpuData();

        // TEST: correct time - morning - returns next meeting date at the set time + duration
        data.setTime("6:15 AM");
        data.setDay("Tuesday");
	    assertEquals(data.getEndTime() - data.getStartTime(), NpuData.DEFAULT_DURATION);

        // TEST: correct time - evening - returns today at the set time + duration
        data.setTime("6:45 PM");
        data.setDay("Friday");
	    assertEquals(data.getEndTime() - data.getStartTime(), NpuData.DEFAULT_DURATION);
    }

    /**
     * {@link NpuData#getRepeatingRule()}
     */
    @Test
    public void testGetRepeatingRule() {
        // set up
        NpuData data = new NpuData();

        // TEST: null case
        assertEquals("", data.getRepeatingRule());

        // TEST: empty String case
        data.setDay("");
        assertEquals("", data.getRepeatingRule());

        // TEST: normal case
        data.setDay("Wednesday");
        assertTrue(data.getRepeatingRule() != null && !data.getRepeatingRule().isEmpty());
    }
}