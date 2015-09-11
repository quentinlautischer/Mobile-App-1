package com.example.quentinlautischer.cmput301_assignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * Created by quentinlautischer on 2015-09-09.
 */
public class BuzzerGameFragment extends Fragment {

    private SeekBar playerNumSeekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.buzzer_game_fragment, container, false);

        playerNumSeekBar = (SeekBar) rootView.findViewById(R.id.buzzerSeekBar);

        playerNumSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int num = 2;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                num = i + 2;
                final TextView mTextView = (TextView) rootView.findViewById(R.id.numPlayersTextView);
                mTextView.setText(String.valueOf(i + 2));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                final TextView mTextView = (TextView) getView().findViewById(R.id.numPlayersTextView);
                mTextView.setText(String.valueOf(num));
            }
        });

        return rootView;
    }

}
