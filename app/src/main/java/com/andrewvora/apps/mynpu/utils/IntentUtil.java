package com.andrewvora.apps.mynpu.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;

import com.andrewvora.apps.mynpu.models.NpuData;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by faytx on 7/3/2016.
 * @author faytxzen
 */
public final class IntentUtil {

    public static final String ENCODING_TYPE = "UTF-8";

    /**
     * Sends an map intent to the Android package manager if such an app exists.
     * @param context - used to get the package manager
     * @param uri - used to give the geo-query to the receiving app
     * @return the sent {@link Intent}
     */
    public static Intent sendMapIntent(Context context, Uri uri) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(uri);

        if(mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }

        return mapIntent;
    }

    /**
     * Sends an {@link Intent#ACTION_INSERT} {@link Intent} to the Calendar Content Provider
     * to add an entry for the given eventData.
     * @param context - used to send the {@link Intent}
     * @param eventData - the {@link NpuData} object to make the event entry for.
     * @return the {@link Intent} sent
     */
    public static Intent sendCalendarIntent(Context context, NpuData eventData) {
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT,
                CalendarContract.Events.CONTENT_URI);

        String title = String.format("%s - %s",
                eventData.getNpu(), eventData.getName());

        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, eventData.getStartTime());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, eventData.getEndTime());
        calendarIntent.putExtra(CalendarContract.Events.RRULE, eventData.getRepeatingRule());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, title);
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, eventData.getLocation());

        if(calendarIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(calendarIntent);
        }

        return calendarIntent;
    }

    /**
     * Creates a geographically appropriate {@link Uri} object using the given address String as a
     * parameter value.<br/>
     * The URI will look like the following "geo:0,0?q={address}"
     * @param address - the address to use as parameter value.
     * @return a {@link Uri} object.
     */
    public static Uri getGeoUriFor(String address) {
        final String base = "geo:0,0?";
        final String safeAddress = address != null ? address : "";

        try {
            address = URLEncoder.encode(safeAddress, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return Uri.parse(base).buildUpon()
                .appendQueryParameter("q", address)
                .build();
    }
}
