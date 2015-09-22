package com.example.quentinlautischer.cmput301_assignment1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by quentinlautischer on 2015-09-11.
 */
public class StatsController extends Application {

    private static final String FILENAME = "reactionTimes.sav";
    private static final String FILENAME_buzzer = "reactionTimes.sav";
    ArrayList<Integer> reactionTimes;
    HashMap<String, Integer> buzzerClicks;

    public StatsController(MainActivity root){

        loadData();
//        loadReactionTimes();
        loadBuzzerClicks();
//        loadReactionTimes();

    }

    private void loadData(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Following line base on https://google-gson.googlecode.com/svn ...
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            reactionTimes = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            reactionTimes = new ArrayList<Integer>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }

    private void saveData(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(reactionTimes, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void clearStats(){
        reactionTimes = new ArrayList<Integer>();
        saveData();
        clearBuzzerClicks();
    }

    //Buzzer Game Methods

    private void clearBuzzerClicks(){
        buzzerClicks = new HashMap<String, Integer>();
        buzzerClicks.put("b2P_p1", 0);
        buzzerClicks.put("b2P_p2", 0);
        buzzerClicks.put("b3P_p1", 0);
        buzzerClicks.put("b3P_p2", 0);
        buzzerClicks.put("b3P_p3", 0);
        buzzerClicks.put("b4P_p1", 0);
        buzzerClicks.put("b4P_p2", 0);
        buzzerClicks.put("b4P_p3", 0);
        buzzerClicks.put("b4P_p4", 0);
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
        String key = "b" + gameMode + "_" + player;
        if (buzzerClicks.containsKey(key)){
            buzzerClicks.put(key, buzzerClicks.get(key) + 1);
        } else { throw new IllegalArgumentException("Key does not correspond to data reference"); }
    }

    public Integer getBuzzerClicks(String key){
        return buzzerClicks.get(key);
    }

    //Reaction Timer Methods

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

    public void addReactionTime(Integer time){
        this.reactionTimes.add(0, time);
        saveData();
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
