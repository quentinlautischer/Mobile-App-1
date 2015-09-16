package com.example.quentinlautischer.cmput301_assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * Created by quentinlautischer on 2015-09-11.
 */
public class StatsController {
//    private static StatsController mInstance = null;
    ArrayList<Integer> reactionTimes;

    MainActivity root;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public StatsController(MainActivity root){

        this.root = root;

        reactionTimes = new ArrayList<Integer>();

        loadReactionTimes();

        sharedPref = root.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }

    private void loadReactionTimes(){
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(100);
        addReactionTime(1000);
    }

    private void saveReactionTimes(){

    }

    public void setSomeStat(int someStat) {
        commitStat(someStat, R.string.reactMinTimeAll);
    }

    private void commitStat(int stat, int identifier){
        editor.putInt(root.getString(identifier), stat);
        Log.d("Storing Value at: ", root.getString(identifier));
        editor.commit();
    }

    public void clearStats(){
       reactionTimes = new ArrayList<Integer>();
    }

    public void addReactionTime(Integer time){
        this.reactionTimes.add(time);
        saveReactionTimes();
    }

    public Integer getMinTimeForLast(Integer xTimes){
        if (xTimes.equals(Integer.MAX_VALUE)){
            xTimes = reactionTimes.size();
        }

        Iterator<Integer> iter = reactionTimes.iterator();
        int count = 0;
        Integer minValue = Integer.MAX_VALUE;
        while(iter.hasNext() && count < xTimes){
            Integer currentValue = iter.next();
            if (currentValue < minValue) {
                minValue = currentValue;
            }
            count++;
        }

        if(minValue.equals(Integer.MAX_VALUE)){
            return 0;
        }

        return minValue;
    }

    public Integer getMaxTimeForLast(Integer xTimes){
        if (xTimes.equals(Integer.MAX_VALUE)){
            xTimes = reactionTimes.size();
        }

        Iterator<Integer> iter = reactionTimes.iterator();
        int count = 0;
        Integer maxValue = 0;
        while(iter.hasNext() && count < xTimes){
            Integer currentValue = iter.next();
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
            count++;
        }

        return maxValue;
    }

    public Integer getAvgTimeForLast(Integer xTimes){
        if (reactionTimes.size() < xTimes){
            xTimes = reactionTimes.size();
        }

        Iterator<Integer> iter = reactionTimes.iterator();
        int count = 0;
        Integer sum = 0;
        while(iter.hasNext() && count < xTimes){
            Integer currentValue = iter.next();
            sum = sum + currentValue;
            count++;
        }

        if (xTimes.equals(0)){
            return 0;
        }
        return (sum / xTimes);
    }


    public Integer getMedTimeForLast(Integer xTimes){
        Integer reactSize = reactionTimes.size();
        if (reactSize < xTimes){
            if (reactSize.equals(0)){
                return 0;
            }
            xTimes = reactionTimes.size();
        }


        List<Integer> list = reactionTimes.subList(0, xTimes);
        Collections.sort(list);

        int middle = list.size()/2;
        if (list.size()%2 == 1) {
            return list.get(middle);
        } else {
            return (list.get(middle-1) + list.get(middle)) / 2;
        }

    }
}
