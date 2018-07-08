package com.andrewvora.apps.mynpu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.IllegalFormatException;
import java.util.Locale;

/**
 * Utility methods for operations related to dates and time.
 * Created by root on 6/3/16.
 * @author faytxzen
 */
public final class DateUtil {

    private static final String READABLE_DATE_FORMAT = "MMMM dd, yyyy";
    private static final String TIME_12H_FORMAT = "hh:mm a";

    public static Date getTimeFromString(String timeStr) {
        // make sure the timeStr isn't null or empty to prevent a parse exception
        if(timeStr != null && !timeStr.isEmpty())
        {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(TIME_12H_FORMAT, Locale.getDefault());
                sdf.setLenient(false);
                return sdf.parse(timeStr);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String getFormattedDate(String time, String day, int occurrence) throws
            IllegalFormatException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(GregorianCalendar.DAY_OF_WEEK, getDayConstant(day));
        calendar.set(GregorianCalendar.DAY_OF_WEEK_IN_MONTH, occurrence);

        return new SimpleDateFormat(READABLE_DATE_FORMAT, Locale.getDefault())
                .format(calendar.getTime()) + " " + time;
    }

    public static int getDayConstant(String day) {
        switch(day.toLowerCase()) {
            case "monday":
                return Calendar.MONDAY;

            case "tuesday":
                return Calendar.TUESDAY;

            case "wednesday":
                return Calendar.WEDNESDAY;

            case "thursday":
                return Calendar.THURSDAY;

            case "friday":
                return Calendar.FRIDAY;

            case "saturday":
                return Calendar.SATURDAY;

            case "sunday":
                return Calendar.SUNDAY;
        }

        return 0;
    }
}
