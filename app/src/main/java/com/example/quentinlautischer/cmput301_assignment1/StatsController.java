package com.example.quentinlautischer.cmput301_assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Observable;

/**
 * Created by quentinlautischer on 2015-09-11.
 */
public class StatsController extends Observable{
//    private static StatsController mInstance = null;

    private int someStat;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor; = sharedPref.edit();
    editor.putInt(getString(R.string.saved_high_score), newHighScore);
    editor.commit();
    private StatsController(){
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

    }


    public int getSomeStat() {
        return someStat;
    }

    public void setSomeStat(int someStat) {

        Log.d("lol", "SETTING");
        this.someStat = someStat;
        setChanged();
        notifyObservers();
    }

}
