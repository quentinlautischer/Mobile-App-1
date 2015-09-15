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


/**
 * Created by quentinlautischer on 2015-09-09.
 */
public class StatsFragment extends Fragment{

    public SharedPreferences sharedPref;

    private MainActivity root;

//    private String[] reactStatFields = {  "reactMinTime10", "reactMinTime100", "reactMinTimeAll",
//                                    "reactMaxTime10", "reactMaxTime100", "reactMaxTimeAll",
//                                    "reactAvgTime10", "reactAvgTime100", "reactAvgTimeAll",
//                                    "reactMedTime10", "reactMedTime100", "reactMedTimeAll" };

    private String[] buzzerStatFields = { "2P_player1", "2P_player2",
                                    "3P_player1", "3P_player2", "3P_player3",
                                    "4P_player1", "4P_player2", "4P_player3", "4P_player4" };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.stats_fragment, container, false);

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        root = (MainActivity) getActivity();

        initStats(rootView);

        rootView.findViewById(R.id.clearStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearStats(view);
            }
        });

        return rootView;
    }

    private void clearStats(View rootView){

        root.statsController.clearStats();
        initStats(rootView);
    }

    private void initStats(View rootView) {
        String [] reactStat = getResources().getStringArray(R.array.reactStats);
        Log.d("RIGHT BEFORE", "THE STORM");
        for (String i : reactStat){
            Log.d("THE FIRST STAT IS: ", i);
            Log.d("GETTING THINGS", "lala");
            String tableNum = getString(getResources().getIdentifier(i, "string", "com.example.quentinlautischer.cmput301_assignment1"));
            Log.d("TableNum", tableNum);

            int value = sharedPref.getInt(tableNum, 0);

            Log.d("Moving On to Text View", "al");

//            TextView mTextView = (TextView) rootView.findViewById(R.id.reactionSTableR2C4);
            TextView mTextView = (TextView) rootView.findViewById(getResources().getIdentifier(tableNum, "id", "com.example.quentinlautischer.cmput301_assignment1"));
            mTextView.setText(String.valueOf(value));

        }

        setStatTableEntity("reactMinTime10");


//        Log.d("Request stat from: ", getString(R.string.reactMinTimeAll));
//        int value = sharedPref.getInt(getString(R.string.reactMinTimeAll), 0);
//        TextView mTextView = (TextView) rootView.findViewById(R.id.reactionStatsTableR2C4);
//        mTextView.setText(String.valueOf(value));
    }


    private void setStatTableEntity(String tableAttribute, Integer value) {
        String tableNum = getString(getResources().getIdentifier(tableAttribute, "string", "com.example.quentinlautischer.cmput301_assignment1"));
        TextView mTextView = (TextView) getActivity().findViewById(getResources().getIdentifier(tableNum, "id", "com.example.quentinlautischer.cmput301_assignment1"));
        mTextView.setText(String.valueOf(value));
    }
}
