package com.example.quentinlautischer.cmput301_assignment1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Random;

public class ReactionTimerFragment extends Fragment{

    private Boolean awaitingClick = false;
    private Timer reactionTimer;

    MainActivity root;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.reaction_timer_fragment, container, false);

        root = (MainActivity) getActivity();
        reactionTimer = new Timer();

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReactionTimerClick();
            }
        });

        this.rootView = rootView;

        return rootView;
    }

    public void ReactionTimerClick( ) {

        if (!awaitingClick){
            //Start and wait for Reaction
            afterClickView();

            Random r = new Random();
            int alertDelayTime = r.nextInt(2000) + 10;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    alertClickView();
                    awaitingClick = true;
                    reactionTimer.start();
                }
            }, alertDelayTime);
        } else {
            //Reaction
            int time = reactionTimer.stop();
            awaitingClick = false;

            setReactionInfoOnView(time);

            root.statsModel.addReactionTime(time);

            alertFinishedView();
        }
    }

    public class Timer {
        private int startTime = 0;

        public void start() {
            this.startTime = (int) System.currentTimeMillis();
        }

        public int stop() {
            return (int) ((System.currentTimeMillis() - this.startTime));
        }
    }

    private void afterClickView(){
        rootView.findViewById(R.id.reactionTimerRoot).setBackgroundColor(Color.parseColor("#5edf74"));
        rootView.findViewById(R.id.reactionTimerTextView).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.reactionTimerAlert).setVisibility(View.INVISIBLE);
    }

    private void alertClickView(){
        getView().findViewById(R.id.reactionTimerAlert).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.reactionTimerRoot).setBackgroundColor(Color.parseColor("#CC1234"));
    }

    private void setReactionInfoOnView(int time){
        final TextView mTextView = (TextView) rootView.findViewById(R.id.reactionTimerTextView);

        mTextView.setText("Tap to begin again \n Your time was: ");
        mTextView.append(String.valueOf(time) + "ms \n");
        if(root.statsModel.getMinTimeForLast(Integer.MAX_VALUE) > time) {
            mTextView.append("NEW RECORD!!");
        }
    }

    private void alertFinishedView(){
        rootView.findViewById(R.id.reactionTimerRoot).setBackgroundColor(Color.parseColor("#5edf74"));
        rootView.findViewById(R.id.reactionTimerTextView).setVisibility(View.VISIBLE);
        rootView.findViewById(R.id.reactionTimerAlert).setVisibility(View.INVISIBLE);
    }

}
