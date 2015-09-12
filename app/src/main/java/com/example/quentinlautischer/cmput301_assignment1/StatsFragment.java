package com.example.quentinlautischer.cmput301_assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by quentinlautischer on 2015-09-09.
 */
public class StatsFragment extends Fragment {

    public SharedPreferences sharedPref;
    public SharedPreferences.Editor editor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.stats_fragment, container, false);

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        initStats(rootView);

        return rootView;
    }

    private void initStats(View rootView) {
        long value = sharedPref.getInt(getString(R.string.reactMinTime10), 0);
        TextView mTextView = (TextView) rootView.findViewById(R.id.reactMinTime10);
        mTextView.setText(String.valueOf(value));
    }


}
