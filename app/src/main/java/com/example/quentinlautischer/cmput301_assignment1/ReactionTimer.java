package com.example.quentinlautischer.cmput301_assignment1;

/**
 * Created by quentinlautischer on 2015-09-10.
 */
public class ReactionTimer {

    private long startTime = 0;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public long stop() {
        return ((System.currentTimeMillis() - this.startTime));
    }

}
