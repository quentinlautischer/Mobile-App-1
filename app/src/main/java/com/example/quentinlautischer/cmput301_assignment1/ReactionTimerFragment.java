package com.example.quentinlautischer.cmput301_assignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by quentinlautischer on 2015-09-09.
 */
public class ReactionTimerFragment extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.reaction_timer_fragment, container, false);

            return rootView;
        }

}
