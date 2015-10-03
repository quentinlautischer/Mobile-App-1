package com.example.quentinlautischer.cmput301_assignment1;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by quentinlautischer on 2015-09-15.
 */

public class StatsModelTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity root;

    public StatsModelTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        root = getActivity();
    }

    public void testGetInstance(){
        StatsModel model = StatsModel.getInstance(root);
        StatsModel modelRef = StatsModel.getInstance();
        assertEquals(model, modelRef);
    }

}
