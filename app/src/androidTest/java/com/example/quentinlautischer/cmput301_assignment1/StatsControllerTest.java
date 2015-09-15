package com.example.quentinlautischer.cmput301_assignment1;

import junit.framework.TestCase;

import com.example.quentinlautischer.cmput301_assignment1.MainActivity;
import com.example.quentinlautischer.cmput301_assignment1.StatsController;

import java.util.ArrayList;

/**
 * Created by quentinlautischer on 2015-09-15.
 */

public class StatsControllerTest extends TestCase {
    public void testReactionList(){
        StatsController statCtrl = new StatsController(new MainActivity());
        assertTrue("Ensure StatsController create ArrayList", statCtrl.reactionTimes.equals(new ArrayList<Integer>()));
    }

}
