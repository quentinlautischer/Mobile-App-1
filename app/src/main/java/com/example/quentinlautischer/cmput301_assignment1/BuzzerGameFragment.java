package com.example.quentinlautischer.cmput301_assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by quentinlautischer on 2015-09-09.
 * Used
 * //http://stackoverflow.com/questions/1851633/how-to-add-a-button-dynamically-in-android
 */
public class BuzzerGameFragment extends Fragment implements Observer {

    private SeekBar playerNumSeekBar;
    private int numOfPlayers = 2;

    MainActivity root;
    View rootView;

    private BuzzerGameController ctrlr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = (MainActivity) getActivity();
        ctrlr = new BuzzerGameController();
        ctrlr.addObserver(this);

        final View rootView = inflater.inflate(R.layout.buzzer_game_fragment, container, false);

        playerNumSeekBar = (SeekBar) rootView.findViewById(R.id.buzzerSeekBar);
        playerNumSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                numOfPlayers = i + 2;
                final TextView mTextView = (TextView) rootView.findViewById(R.id.numPlayersTextView);
                mTextView.setText(String.valueOf(i + 2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                final TextView mTextView = (TextView) root.findViewById(R.id.numPlayersTextView);
                mTextView.setText(String.valueOf(numOfPlayers));
            }
        });

        rootView.findViewById(R.id.buzzerBeginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buzzerButtonView();
            }
        });

        this.rootView = rootView;
        return rootView;
    }

    @Override
    public void update(Observable observable, Object data){}

    public void buzzerButtonView(){
        final LinearLayout linear = (LinearLayout) rootView.findViewById(R.id.buzzer_button_layout);
        for(int i=0; i < numOfPlayers; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    (float) 1/numOfPlayers);
            Button btn = new Button(root);
            btn.setId(i + 1);
            final int id_ = btn.getId();
            btn.setText("Player " + id_);
            btn.setBackgroundColor(Color.rgb(20 * (i + 1), 90 * (i + 1), 50 * (i + 1)));
            btn.setTextColor(Color.rgb(200, 200, 200));
            linear.addView(btn, params);
            Button btn1 = ((Button) rootView.findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ctrlr.addBuzzerClick(String.valueOf(numOfPlayers) + "P", "p" + id_);

                    AlertDialog.Builder alert = new AlertDialog.Builder(root);
                    alert.setTitle("Player " + id_ + " Won!");

                    alert.setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {}
                    });
                    alert.setNegativeButton("Return",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    rootView.findViewById(R.id.buzzer_setup_layout).setVisibility(View.VISIBLE);
                                    linear.removeAllViews();
                                }
                            });
                    alert.show();
                }
            });
        }
        rootView.findViewById(R.id.buzzer_setup_layout).setVisibility(View.INVISIBLE);
        linear.setVisibility(View.VISIBLE);
    }
}
