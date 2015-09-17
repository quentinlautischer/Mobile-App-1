package com.example.quentinlautischer.cmput301_assignment1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by quentinlautischer on 2015-09-11.
 */
public class StatsController extends Application {
//    private static StatsController mInstance = null;
    ArrayList<Integer> reactionTimes;
    HashMap<String, Integer> buzzerClicks;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public StatsController(MainActivity root){

        reactionTimes = new ArrayList<Integer>();
        loadBuzzerClicks();


        loadReactionTimes();

        sharedPref = root.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }

    private void loadBuzzerClicks(){
        buzzerClicks = new HashMap<String, Integer>();
        buzzerClicks.put("b2P_p1", 0);
        buzzerClicks.put("b2P_p2", 0);
        buzzerClicks.put("b3P_p1", 2);
        buzzerClicks.put("b3P_p2", 0);
        buzzerClicks.put("b3P_p3", 0);
        buzzerClicks.put("b4P_p1", 0);
        buzzerClicks.put("b4P_p2", 5);
        buzzerClicks.put("b4P_p3", 0);
        buzzerClicks.put("b4P_p4", 1);
    }

    public void addBuzzerClick(String gameMode, String player){
        Log.d("la", "Im getting called bro" + String.valueOf(this));

        String key = "b" + gameMode + "_" + player;
        if (buzzerClicks.containsKey(key)){
            Log.d("la", "Ive made it into the loop");
            buzzerClicks.put(key, buzzerClicks.get(key) + 1);
            Log.d("la", buzzerClicks.toString());
        } else { throw new IllegalArgumentException("Key does not correspond to data reference"); }
    }

    public Integer getBuzzerClicks(String key){
        return buzzerClicks.get(key);
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

//        String sd = sharedPref.getString("rectionTimes", "0");
//        dd = sd.DESERIALIZE;
    }


    private void saveReactionTimes(){
//        Serial sd = SOMESERIALIZING_METHOD;
//        editor.putStringSet("reactionTimes", serialData);
//        editor.commit();
    }

    public void clearStats(){
       reactionTimes = new ArrayList<Integer>();
    }

    public void addReactionTime(Integer time){
        this.reactionTimes.add(0, time);
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
