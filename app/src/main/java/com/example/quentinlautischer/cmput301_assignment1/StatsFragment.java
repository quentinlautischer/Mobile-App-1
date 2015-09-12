package com.example.quentinlautischer.cmput301_assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by quentinlautischer on 2015-09-09.
 */
public class StatsFragment extends Fragment implements Observer{

    public SharedPreferences sharedPref;
    public SharedPreferences.Editor editor;

    MainActivity root;

    View _rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.stats_fragment, container, false);

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        root = (MainActivity) getActivity();
        root.getObserver().addObserver(this);
        Log.d("LOL", "Stats added as an observer");

        _rootView = rootView;

//        initStats(rootView);

        return rootView;
    }

    @Override
    public void update(Observable observable, Object data){
        Log.d("lol", "statUpdate");
        int i = root.getObserver().getSomeStat();
        TextView mTextView = (TextView) _rootView.findViewById(R.id.reactMinTime10);
        mTextView.setText(String.valueOf(i));

    }

    private void initStats(View rootView) {
        long value = sharedPref.getInt(getString(R.string.reactMinTime10), 0);
        TextView mTextView = (TextView) rootView.findViewById(R.id.reactMinTime10);
        mTextView.setText(String.valueOf(value));
    }


}
