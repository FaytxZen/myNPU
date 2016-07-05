package com.andrewvora.apps.planforatlanta.models;

import com.andrewvora.apps.planforatlanta.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class NpuData {
    public static final long DEFAULT_DURATION = 60 * 60 * 1000; // one hour

    private String npuName;
    private String meetingName;
    private String location;
    private String day;
    private String time;
    private String frequency;
    private int occurrence;
    private int color;

    /**
     * @return the start time for this instance in milliseconds.
     */
    public long getStartTime() {
        // null-check
        Date meetingTime = DateUtil.getTimeFromString(getTime());
        if(meetingTime == null) return 0L;

        // get the time in milliseconds
        Calendar meetingTimeCal = Calendar.getInstance();
        int year = meetingTimeCal.get(Calendar.YEAR);
        int month = meetingTimeCal.get(Calendar.MONTH);

        meetingTimeCal.setTimeInMillis(meetingTime.getTime());
        meetingTimeCal.set(Calendar.YEAR, year);
        meetingTimeCal.set(Calendar.MONTH, month);
        meetingTimeCal.set(Calendar.DAY_OF_WEEK, DateUtil.getDayConstant(getDay()));
        meetingTimeCal.set(Calendar.DAY_OF_WEEK_IN_MONTH, getOccurrence());
        meetingTimeCal.set(Calendar.HOUR, meetingTimeCal.get(Calendar.HOUR));
        meetingTimeCal.set(Calendar.MINUTE, meetingTimeCal.get(Calendar.MINUTE));
        meetingTimeCal.set(Calendar.SECOND, 0);
        meetingTimeCal.set(Calendar.MILLISECOND, 0);

        return meetingTimeCal.getTimeInMillis();
    }

    /**
     * @return the end time for this instance in milliseconds. If no duration is specified,
     * {@link this#DEFAULT_DURATION} will be used.
     */
    public long getEndTime() {
        return getStartTime() + DEFAULT_DURATION;
    }

    /**
     * @return the {@link android.provider.CalendarContract.Events#RRULE} String
     */
    public String getRepeatingRule() {
        // occurrence of the day of the week
        String repeatOnDay = String.valueOf(getOccurrence());

        // which day of the week
        repeatOnDay = repeatOnDay.concat(getDay().substring(0, 2).toUpperCase());

        // number of months
        Calendar calendar = Calendar.getInstance();
        int numMonthsRemaining = Calendar.DECEMBER - calendar.get(Calendar.MONTH);

        return String.format(Locale.getDefault(), "FREQ=MONTHLY;COUNT=%d;BYDAY=%s",
                numMonthsRemaining, repeatOnDay);
    }

    public String getNpuName() {
        return npuName;
    }

    public void setNpuName(String npuName) {
        this.npuName = npuName;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
