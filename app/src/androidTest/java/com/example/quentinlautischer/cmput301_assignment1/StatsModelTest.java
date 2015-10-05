package com.example.quentinlautischer.cmput301_assignment1;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by quentinlautischer on 2015-09-15.
 */

public class StatsModelTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity root;
    StatsModel model;

    public StatsModelTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        root = getActivity();
        model = StatsModel.getInstance(root);
    }

    @Override
    protected void tearDown() throws Exception {
        model.clearStats();
        super.tearDown();
    }

    public void testGetInstance(){
        StatsModel modelRef = StatsModel.getInstance();
        assertEquals(model, modelRef);
    }

    public void testGetMedForLast(){
        model.addReactionTime(100);
        model.addReactionTime(200);
        model.addReactionTime(300);

        Integer testValue = model.getMedTimeForLast(10);

        assertEquals(new Integer(200), testValue);
    }

    public void testGetAvgForLast(){
        model.addReactionTime(100);
        model.addReactionTime(200);
        model.addReactionTime(300);

        Integer testValue = model.getAvgTimeForLast(10);

        assertEquals(new Integer(200), testValue);
    }

    public void testGetMinForLast(){
        model.addReactionTime(100);
        model.addReactionTime(200);
        model.addReactionTime(300);

        Integer testValue = model.getMinTimeForLast(10);

        assertEquals(Integer.valueOf(100), testValue);
    }

    public void testGetMaxForLast(){
        model.addReactionTime(100);
        model.addReactionTime(200);
        model.addReactionTime(300);

        Integer testValue = model.getMaxTimeForLast(10);

        assertEquals(Integer.valueOf(300), testValue);
    }

    public void testAddReactionTime(){
        assertTrue(model.reactionTimes.size() == 0);
        model.addReactionTime(100);
        assertTrue(model.reactionTimes.size() == 1);
        model.addReactionTime(200);
        assertTrue(model.reactionTimes.size() == 2);

    }

    public void testAdd_and_GetBuzzerClicks(){
        model.clearStats();
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b2P_p1"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b2P_p2"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b3P_p1"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b3P_p2"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b3P_p3"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b4P_p1"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b4P_p2"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b4P_p3"));
        assertEquals(Integer.valueOf(0), model.getBuzzerClicks("b4P_p4"));

        model.addBuzzerClick("2P", "p1");

        assertEquals(Integer.valueOf(1), model.getBuzzerClicks("b2P_p1"));


    }

}
