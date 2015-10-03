package com.example.quentinlautischer.cmput301_assignment1;

import java.util.Observable;

/**
 * Created by lautisch on 10/2/15.
 */
public class BuzzerGameController extends Observable {

    private StatsModel model;

    public BuzzerGameController(){
        model = StatsModel.getInstance();
    }

    public void addBuzzerClick(String numOfPlayers, String playerNum){
        model.addBuzzerClick(numOfPlayers, playerNum);
    }

}
