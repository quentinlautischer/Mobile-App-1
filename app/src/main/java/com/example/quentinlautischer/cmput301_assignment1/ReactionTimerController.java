package com.example.quentinlautischer.cmput301_assignment1;

/**
 * Created by lautisch on 10/2/15.
 */
public class ReactionTimerController {

    private Boolean awaitingClick = false;
    public Timer reactionTimer;
    private StatsModel model;

    public ReactionTimerController(){
        reactionTimer = new Timer();
        model = StatsModel.getInstance();
    }

    public Boolean getAwaitingClick() {
        return awaitingClick;
    }

    public void setAwaitingClick(Boolean awaitingClick) {
        this.awaitingClick = awaitingClick;
    }

    public int getMinTime(){
        return model.getMinTimeForLast(Integer.MAX_VALUE);
    }

    public class Timer {
        private int startTime = 0;

        public void start() {
            this.startTime = (int) System.currentTimeMillis();
        }

        public int stop() {
            int time = (int) ((System.currentTimeMillis() - this.startTime));
            //ALERT MODEL ADD STAT
            return time;
        }
    }

}
