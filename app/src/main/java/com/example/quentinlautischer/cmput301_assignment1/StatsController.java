package com.example.quentinlautischer.cmput301_assignment1;

/**
 * Created by quentinlautischer on 2015-09-11.
 */
public class StatsController {
    private static StatsController mInstance = null;



    private int someStat;

    private StatsController(){

    }

    public static StatsController getInstance(){
        if(mInstance == null){
            mInstance = new StatsController();
        }
        return mInstance;
    }

    public int getSomeStat() {
        return someStat;
    }

    public void setSomeStat(int someStat) {
        this.someStat = someStat;
    }

}
