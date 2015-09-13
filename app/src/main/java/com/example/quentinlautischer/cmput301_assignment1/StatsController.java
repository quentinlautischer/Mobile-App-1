package com.example.quentinlautischer.cmput301_assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Observable;

/**
 * Created by quentinlautischer on 2015-09-11.
 */
public class StatsController {
//    private static StatsController mInstance = null;

    MainActivity root;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public StatsController(MainActivity _root){

        root = _root;

        sharedPref = root.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }


//    public int getSomeStat() {
//        return someStat;
//    }

    public void setSomeStat(int someStat) {
        commitStat(someStat, R.string.reactMinTimeAll);

    }

    private void commitStat(int stat, int identifier){
        editor.putInt(root.getString(identifier), stat);
        Log.d("Storing Value at: ", root.getString(identifier));
        editor.commit();
    }

    public void addReactionClick(int ReactionTime){

    }

    public void clearStats(){
        String [] reactStat =  root.getResources().getStringArray(R.array.reactStats);
        for (String i : reactStat){
            editor.putInt(root.getString(root.getResources().getIdentifier(i, "string", "com.example.quentinlautischer.cmput301_assignment1")), 0);
        }
        editor.commit();
    }

}
