package com.andrewvora.apps.planforatlanta.models;

/**
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public class NpuData {
    private String npuName;
    private String meetingName;
    private String location;
    private String day;
    private String time;
    private String frequency;
    private int occurrence;
    private int color;

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
