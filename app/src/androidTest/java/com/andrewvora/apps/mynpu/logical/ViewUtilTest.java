package com.andrewvora.apps.mynpu.logical;

import android.content.Context;
import android.content.res.Resources;

import com.andrewvora.apps.mynpu.common.BaseInstrumentedUnitTest;
import com.andrewvora.apps.mynpu.utils.ViewUtil;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Tests for the {@link ViewUtil} utility class.
 *
 * Created by faytx on 8/20/2016.
 * @author faytxzen
 */
public class ViewUtilTest extends BaseInstrumentedUnitTest {

    /**
     * Tests various scenarios of {@link ViewUtil#getRandomColor(Context)}
     */
    @Test
    public void testGetRandomColor() {
        int[] colors = { 1, 2, 3, 4 };

        Resources resources = Mockito.mock(Resources.class);
        when(resources.getIntArray(anyInt())).thenReturn(colors);

        Context context = Mockito.mock(Context.class);
        when(context.getResources()).thenReturn(resources);

        // TEST: normal case
        assertTrue(ViewUtil.getRandomColor(context) > 0);

        // TEST: single color case
        when(resources.getIntArray(anyInt())).thenReturn(new int[] { 1 });
        assertTrue(ViewUtil.getRandomColor(context) > 0);
    }
}
