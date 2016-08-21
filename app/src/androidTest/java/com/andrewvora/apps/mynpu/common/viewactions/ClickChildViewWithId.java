package com.andrewvora.apps.mynpu.common.viewactions;

import android.support.annotation.IdRes;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Clicks on a specific View within a parent View.
 * This was originally written to be used with a RecyclerView.
 *
 * Created by faytx on 8/20/2016.
 * @author faytxzen
 */
public class ClickChildViewWithId implements ViewAction {

    @IdRes private int mViewId;

    public ClickChildViewWithId(@IdRes int id) {
        mViewId = id;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View v = view.findViewById(mViewId);

        if(v != null) {
            v.performClick();
        }
    }

    @Override
    public String getDescription() {
        return "Click on a child view with a specific ID";
    }

    /**
     * Convenience method for this {@link ViewAction} class.
     * @param id - the resource ID for the {@link View} you want to click.
     * @return a configured instance of the {@link ClickChildViewWithId} class.
     */
    public static ClickChildViewWithId clickViewWithId(@IdRes int id) {
        return new ClickChildViewWithId(id);
    }
}
