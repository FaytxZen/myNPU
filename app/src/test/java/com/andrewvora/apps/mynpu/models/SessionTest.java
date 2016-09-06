package com.andrewvora.apps.mynpu.models;

import com.andrewvora.apps.mynpu.Session;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by faytx on 8/20/2016.
 * @author faytxzen
 */
public class SessionTest {

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull(Session.getInstance());
    }

    @Test
    public void testGetNpuMap() throws Exception {

    }

    @Test
    public void testSetNpuMap() throws Exception {

    }
}