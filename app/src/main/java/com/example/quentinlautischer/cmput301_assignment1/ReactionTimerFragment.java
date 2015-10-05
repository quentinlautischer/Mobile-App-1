package com.example.quentinlautischer.cmput301_assignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by quentinlautischer on 2015-09-09.
 */

public class ReactionTimerFragment extends Fragment implements Observer{

    View rootView;

    private ReactionTimerController ctrlr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.reaction_timer_fragment, container, false);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrlr.ReactionTimerClick();
            }
        });

        this.rootView = rootView;
        ctrlr = new ReactionTimerController();
        ctrlr.addObserver(this);

        return rootView;
    }

    @Override
    public void update(Observable observable, Object data){

        switch (ctrlr.getReactionDataUpdate()) {
            case R.string.startReactionTimer:
                afterClickView();
                break;
            case R.string.alertReactionTimer:
                alertClickView();
                break;
            case R.string.finishReactionTimer:
                setReactionInfoOnView(ctrlr.getLastTime());
                alertFinishedView();
                break;
            case R.string.resetReactionTimer:
                resetClickView();
            default:
                break;
        }
    }

    public void resetClickView(){
        final TextView mTextView = (TextView) rootView.findViewById(R.id.reactionTimerTextView);

        mTextView.setText("You Tapped Too Soon \n Tap to begin again \n");

        rootView.findViewById(R.id.reactionTimerRoot).setBackgroundColor(getResources().getColor(R.color.reactionTimerDefaultBG));
        rootView.findViewById(R.id.reactionTimerTextView).setVisibility(View.VISIBLE);
        rootView.findViewById(R.id.reactionTimerAlert).setVisibility(View.INVISIBLE);
    }

    public void afterClickView(){
        rootView.findViewById(R.id.reactionTimerRoot).setBackgroundColor(getResources().getColor(R.color.reactionTimerDefaultBG));
        rootView.findViewById(R.id.reactionTimerTextView).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.reactionTimerAlert).setVisibility(View.INVISIBLE);
    }

    public void alertClickView(){
        getView().findViewById(R.id.reactionTimerAlert).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.reactionTimerRoot).setBackgroundColor(getResources().getColor(R.color.reactionTimerAlertBG));
    }

    public void setReactionInfoOnView(int time){
        final TextView mTextView = (TextView) rootView.findViewById(R.id.reactionTimerTextView);

        mTextView.setText("Tap to begin again \n Your time was: ");
        mTextView.append(String.valueOf(time) + "ms \n");
        if(ctrlr.getMinTime() >= time) {
            mTextView.append("NEW RECORD!!");
        }
    }

    public void alertFinishedView(){
        rootView.findViewById(R.id.reactionTimerRoot).setBackgroundColor(getResources().getColor(R.color.reactionTimerDefaultBG));
        rootView.findViewById(R.id.reactionTimerTextView).setVisibility(View.VISIBLE);
        rootView.findViewById(R.id.reactionTimerAlert).setVisibility(View.INVISIBLE);
    }

}
