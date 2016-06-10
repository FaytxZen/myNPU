package com.andrewvora.apps.planforatlanta.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.andrewvora.apps.planforatlanta.R;

/**
 * Created by root on 6/2/16.
 * @author faytxzen
 */
public class BaseActivity extends AppCompatActivity {

    private boolean mUseDefaultOptionsMenu = true;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mUseDefaultOptionsMenu) getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_settings:
                Intent preferenceIntent = new Intent(this, SettingsActivity.class);
                startActivity(preferenceIntent);
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

    protected void setUseDefaultOptionsMenu(boolean hasMenu) {
        mUseDefaultOptionsMenu = hasMenu;
    }
}
