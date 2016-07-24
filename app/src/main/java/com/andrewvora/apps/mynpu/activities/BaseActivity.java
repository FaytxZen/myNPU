package com.andrewvora.apps.mynpu.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.andrewvora.apps.mynpu.R;

/**
 * Created by root on 6/2/16.
 * @author faytxzen
 */
public class BaseActivity extends AppCompatActivity {

    private boolean mUseDefaultOptionsMenu = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fabric.with(this, new Crashlytics());
    }

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

            /* Uncomment once any settings-controlled features are implemented
            case R.id.menu_settings:
                Intent preferenceIntent = new Intent(this, SettingsActivity.class);
                startActivity(preferenceIntent);
                break;
            */

            case R.id.menu_feedback:
                sendFeedback();
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

    private void sendFeedback() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.app_email)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_subject_feedback));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
