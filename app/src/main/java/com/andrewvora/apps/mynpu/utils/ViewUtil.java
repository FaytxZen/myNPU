package com.andrewvora.apps.mynpu.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.andrewvora.apps.mynpu.R;

import java.util.Random;

/**
 * Created by root on 5/30/16.
 * @author faytxzen
 */
public final class ViewUtil {

    /**
     * Hides the soft keyboard for the current screen.
     * @param view - {@link View} instance used to grab the context information.
     */
    public static void hideSoftKeyboard(View view) {
        String service = Context.INPUT_METHOD_SERVICE;
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(service);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Returns a random color from the designated list of pre-selected colors.
     * @param context - {@link Context} used to grab the array of random colors.
     * @return a random color selected by using a random index.
     */
    public static int getRandomColor(Context context) {
        int[] colors = context.getResources().getIntArray(R.array.random_colors);
        Random random = new Random();

        return colors[random.nextInt(colors.length)];
    }
}
