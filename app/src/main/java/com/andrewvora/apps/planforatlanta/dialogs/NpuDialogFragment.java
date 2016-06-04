package com.andrewvora.apps.planforatlanta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.andrewvora.apps.planforatlanta.adapters.NpuAdapter;
import com.andrewvora.apps.planforatlanta.R;

/**
 * Created by root on 5/30/16.
 * @author faytxzen
 */
public class NpuDialogFragment extends DialogFragment {
    public static final String TAG = NpuDialogFragment.class.getSimpleName();
    public static final String TAG_SELECTED_INDEX = "SelectedNpuIndex";

    private int mSelectedIndex = -1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // see if an index was passed as an extra
        if(getArguments() != null) mSelectedIndex = getArguments().getInt(TAG_SELECTED_INDEX, -1);

        // fetch the list of NPUs and create an adapter
        String[] npuList = getResources().getStringArray(R.array.list_npus);
        final NpuAdapter npuAdapter = new NpuAdapter(getActivity(), npuList);
        npuAdapter.setSelectedIndex(mSelectedIndex);

        // configure the dialog
        builder.setTitle(R.string.title_dialog_select_npu);
        builder.setAdapter(npuAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSelectedIndex = which;
                ((EventListener) getActivity()).onNpuSelected(mSelectedIndex);
                dismiss();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);

        // return the constructed instance of the AlertDialog
        return builder.create();
    }

    public interface EventListener {
        void onNpuSelected(int index);
    }
}
