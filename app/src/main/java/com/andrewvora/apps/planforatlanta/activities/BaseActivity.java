package com.andrewvora.apps.planforatlanta.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by root on 6/2/16.
 * @author faytxzen
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        else super.onBackPressed();
    }
}
