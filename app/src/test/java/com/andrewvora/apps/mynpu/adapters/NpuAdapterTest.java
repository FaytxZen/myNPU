package com.andrewvora.apps.mynpu.adapters;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by faytx on 7/23/2016.
 * @author faytxzen
 */
public class NpuAdapterTest {

    @Test
    public void testGetAndSetSelectedIndex() throws Exception {
        Context context = Mockito.mock(Context.class);
        NpuAdapter adapter = new NpuAdapter(context, new String[] { "NPU A", "NPU B" });

        // TEST: selected position defaults to 0
        assertEquals(0, adapter.getSelectedIndex());

        // TEST: making sure set works
        adapter.setSelectedIndex(1);
        assertEquals(1, adapter.getSelectedIndex());
    }
}