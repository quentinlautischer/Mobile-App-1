package com.example.quentinlautischer.cmput301_assignment1;

import android.app.Application;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by quentinlautischer on 2015-09-11.
 * Used for Singleton implementation
 * //https://gist.github.com/Akayh/5566992
 */
public class StatsModel extends Application{

    private static StatsModel mInstance = null;

    ArrayList<Integer> reactionTimes;
    HashMap<String, Integer> buzzerClicks;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor;

    String[] buzzerFields = {"b2P_p1","b2P_p2",
                            "b3P_p1","b3P_p2","b3P_p3",
                            "b4P_p1","b4P_p2","b4P_p3","b4P_p4"};

    private StatsModel(MainActivity root){
        sharedPref = root.getPreferences(MODE_PRIVATE);
        editor = sharedPref.edit();

        loadData();
    }

    //https://gist.github.com/Akayh/5566992
    public static StatsModel getInstance(MainActivity root){
        if(mInstance == null){
            mInstance = new StatsModel(root);
        }
        return mInstance;
    }

    public static StatsModel getInstance(){
        return mInstance;
    }

    public String getStatsDataPrinted() {
        String printedData = "Json Formatted Data \n\n Array of Reaction Times: \n "
                + getStatsReactionDataPrinted() + "\n\n HashMap of Buzzer Scores: \n";
        for(String i: buzzerFields){
            printedData = printedData + (i.replace("b", "") + ": " + buzzerClicks.get(i).toString() + "\n");
        }

        return printedData;
    }

    public void loadData(){
        Gson gson = new Gson();
        reactionTimes = new ArrayList<Integer>();
        String reactionTimeJson = sharedPref.getString("reactionTimesData", gson.toJson(reactionTimes));
        Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
        reactionTimes = gson.fromJson(reactionTimeJson, listType);

        buzzerClicks = new HashMap<String, Integer>();
        for(String entry: buzzerFields){
            Integer value = sharedPref.getInt(entry, 0);
            buzzerClicks.put(entry, value);
        }
    }


    private void saveData(){
        Gson gson = new Gson();

        editor.putString("reactionTimesData", gson.toJson(reactionTimes));
        editor.commit();

        for(String entry: buzzerFields){
            Integer value = buzzerClicks.get(entry);
            editor.putInt(entry, value);
        }
        editor.commit();
    }

    public void clearStats(){
        reactionTimes = new ArrayList<Integer>();
        clearBuzzerClicks();
        saveData();
    }

    //Buzzer Game Methods

    public String getStatsBuzzerDataPrinted(){
        Gson gson = new Gson();
        String data = gson.toJson(buzzerClicks);
        return data;
    }

    private void clearBuzzerClicks(){
        buzzerClicks = new HashMap<String, Integer>();
        for (String entry: buzzerFields) {
            buzzerClicks.put(entry, 0);
        }
    }

    public void addBuzzerClick(String gameMode, String player){
        String key = "b" + gameMode + "_" + player;
        if (buzzerClicks.containsKey(key)){
            Integer value =  buzzerClicks.get(key) + 1;
            buzzerClicks.put(key, value);
            editor.putInt(key, value);
            editor.commit();
        } else { throw new IllegalArgumentException("Key does not correspond to data reference"); }
    }

    public Integer getBuzzerClicks(String key){
        return buzzerClicks.get(key);
    }

    //Reaction Timer Methods

    public String getStatsReactionDataPrinted(){
        Gson gson = new Gson();
        String data = gson.toJson(reactionTimes);
        return data;
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
