package com.andrewvora.apps.mynpu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andrewvora.apps.mynpu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 5/30/16.
 * @author faytxzen
 */
public class NpuAdapter extends ArrayAdapter<String> {
    /*===========================================*
     * Member Variables
     *===========================================*/
    private int mSelectedIndex;

    /*===========================================*
     * Constructors
     *===========================================*/
    public NpuAdapter(Context context, String[] objects) {
        super(context, R.layout.element_dialog_list_item, R.id.list_item_title, objects);
    }

    /*===========================================*
     * Inner Classes
     *===========================================*/
    public static class ViewHolder {
        @BindView(R.id.list_item_title) TextView titleView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /*===========================================*
     * Public Methods
     *===========================================*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // set up
        ViewHolder viewHolder;

        // check if the convertView has been inflated yet
        if(convertView == null) {
            // if not, inflate the layout
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.element_dialog_list_item, parent, false);
            // instantiate the ViewHolder
            viewHolder = new ViewHolder(convertView);
            // set its tag
            convertView.setTag(viewHolder);
        }
        else {
            // recover the ViewHolder by its tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set the View properties
        if(getItem(position) != null) {
            viewHolder.titleView.setText(getItem(position));

            int drawableRes = mSelectedIndex == position ? R.drawable.ic_check_24dp : 0;
            viewHolder.titleView.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableRes, 0);
        }

        return convertView;
    }

    public void setSelectedIndex(int index) {
        mSelectedIndex = index;
    }
}
