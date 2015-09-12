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

    MainActivity root;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public StatsController(MainActivity _root){

        root = _root;

        sharedPref = root.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }


    public int getSomeStat() {
        return someStat;
    }

    public void setSomeStat(int someStat) {

        Log.d("lol", "SETTING");
        this.someStat = someStat;

        editor.putInt(root.getString(R.string.reactMinTime10), someStat);
        editor.commit();
        setChanged();
        notifyObservers();
    }

}
