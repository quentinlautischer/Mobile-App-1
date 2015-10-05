package com.example.quentinlautischer.cmput301_assignment1;


import android.os.Handler;
import java.util.Observable;
import java.util.Random;

/**
 * Created by quentinlautischer on 10/2/15.
 */
public class ReactionTimerController extends Observable{

    private Boolean awaitingReactionClick = false;
    private Boolean awaitingAlert = true;

    final Handler handler;
    private Runnable alertRunner;

    public Timer reactionTimer;
    private StatsModel model;

    private int reactionData = 0;
    private int lastTime;

    public ReactionTimerController(){
        reactionTimer = new Timer();
        model = StatsModel.getInstance();
        handler = new Handler();
        alertRunner = new Runnable() {
            @Override
            public void run() {
                setReactionData(R.string.alertReactionTimer);
                setAwaitingAlert(Boolean.FALSE);
                reactionTimer.start();
            }
        };
    }

    public void ReactionTimerClick() {

         if (getAwaitingAlert() && getAwaitingReactionClick()) {
             //Reset pressed too soon
             handler.removeCallbacks(alertRunner);
             setAwaitingAlert(Boolean.TRUE);
             setAwaitingReactionClick(Boolean.FALSE);
             reactionTimer.stop();
             setReactionData(R.string.resetReactionTimer);
         } else if (!getAwaitingReactionClick()){
            //Start and wait for Reaction
            setReactionData(R.string.startReactionTimer);
            setAwaitingAlert(Boolean.TRUE);
             setAwaitingReactionClick(Boolean.TRUE);

            Random r = new Random();
            int alertDelayTime = r.nextInt(2000) + 10;
            handler.postDelayed(alertRunner, alertDelayTime);
        } else if (!getAwaitingAlert()) {
            //Reaction
            int time = reactionTimer.stop();
            setAwaitingReactionClick(Boolean.FALSE);
            setLastTime(time);
            model.addReactionTime(time);
            setReactionData(R.string.finishReactionTimer);
        }
    }

    public int getReactionDataUpdate(){
        return this.reactionData;
    }

    public void setReactionData(int i){
        this.reactionData = i;
        setChanged();
        notifyObservers();
    }

    public Boolean getAwaitingReactionClick() {
        return awaitingReactionClick;
    }

    public void setAwaitingReactionClick(Boolean awaitingReactionClick) {
        this.awaitingReactionClick = awaitingReactionClick;
    }

    public Boolean getAwaitingAlert() {
        return awaitingAlert;
    }

    public void setAwaitingAlert(Boolean awaitingAlert) {
        this.awaitingAlert = awaitingAlert;
    }

    public int getLastTime(){
        return this.lastTime;
    }

    public void setLastTime(int time){
        this.lastTime = time;
    }

    public Integer getMinTime(){
        return model.getMinTimeForLast(Integer.MAX_VALUE);
    }

    public class Timer {
        private int startTime = 0;

        public void start() {
            this.startTime = (int) System.currentTimeMillis();
        }

        public int stop() {
            int time = (int) ((System.currentTimeMillis() - this.startTime));
            return time;
        }
    }

}
