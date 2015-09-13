package com.example.quentinlautischer.cmput301_assignment1;

import android.content.Intent;
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
    private int numOfPlayers = 2;

    private MainActivity root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = (MainActivity) getActivity();

        final View rootView = inflater.inflate(R.layout.buzzer_game_fragment, container, false);

        playerNumSeekBar = (SeekBar) rootView.findViewById(R.id.buzzerSeekBar);


        rootView.findViewById(R.id.buzzerBeginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Load new view
                Intent nextScreen = new Intent(root.getApplicationContext(), BuzzerGameActivity.class);
                startActivity(nextScreen);
            }
        });

        playerNumSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                numOfPlayers = i + 2;
                final TextView mTextView = (TextView) rootView.findViewById(R.id.numPlayersTextView);
                mTextView.setText(String.valueOf(i + 2));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                final TextView mTextView = (TextView) root.findViewById(R.id.numPlayersTextView);
                mTextView.setText(String.valueOf(numOfPlayers));
            }
        });

        return rootView;
    }

}
