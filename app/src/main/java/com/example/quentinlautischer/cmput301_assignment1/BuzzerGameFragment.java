package com.example.quentinlautischer.cmput301_assignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Created by quentinlautischer on 2015-09-09.
 */
public class BuzzerGameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.buzzer_game_fragment, container, false);
        return rootView;
    }

}
