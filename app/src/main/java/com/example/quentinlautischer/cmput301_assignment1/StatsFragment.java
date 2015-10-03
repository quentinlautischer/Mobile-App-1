package com.example.quentinlautischer.cmput301_assignment1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private View rootView;
    private StatsModel model;

    private String[] buzzerStatFields = { "b2P_p1", "b2P_p2",
                                    "b3P_p1", "b3P_p2", "b3P_p3",
                                    "b4P_p1", "b4P_p2", "b4P_p3", "b4P_p4" };

    @Override
    public void setMenuVisibility(final boolean visible){
        super.setMenuVisibility(visible);
        if (visible && isResumed()){
            refreshStatsView();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.stats_fragment, container, false);

        this.sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        this.root = (MainActivity) getActivity();
        this.rootView = rootView;
        model = StatsModel.getInstance();
        refreshStatsView();

        rootView.findViewById(R.id.emailStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root.startEmail();
            }
        });

        rootView.findViewById(R.id.clearStats).getBackground().setColorFilter(0xAAFF0000, PorterDuff.Mode.MULTIPLY);

        rootView.findViewById(R.id.clearStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(root);
                alert.setTitle("Are you sure you want to clear stats?");

                alert.setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        model.clearStats();
                        refreshStatsView();
                    }
                });

                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int whichButton) {}});
                alert.show();
            }
        });

        return rootView;
    }

    private void refreshStatsView() {
        setStatTableEntity("reactMinTime10", model.getMinTimeForLast(10));
        setStatTableEntity("reactMinTime100", model.getMinTimeForLast(100));
        setStatTableEntity("reactMinTimeAll", model.getMinTimeForLast(Integer.MAX_VALUE));

        setStatTableEntity("reactMaxTime10", model.getMaxTimeForLast(10));
        setStatTableEntity("reactMaxTime100", model.getMaxTimeForLast(100));
        setStatTableEntity("reactMaxTimeAll", model.getMaxTimeForLast(Integer.MAX_VALUE));

        setStatTableEntity("reactAvgTime10", model.getAvgTimeForLast(10));
        setStatTableEntity("reactAvgTime100", model.getAvgTimeForLast(100));
        setStatTableEntity("reactAvgTimeAll", model.getAvgTimeForLast(Integer.MAX_VALUE));

        setStatTableEntity("reactMedTime10", model.getMedTimeForLast(10));
        setStatTableEntity("reactMedTime100", model.getMedTimeForLast(100));
        setStatTableEntity("reactMedTimeAll", model.getMedTimeForLast(Integer.MAX_VALUE));

        for(String key: buzzerStatFields){
            setStatTableEntity(key, model.getBuzzerClicks(key));
        }
    }

    private void setStatTableEntity(String tableAttribute, Integer value) {
        String tableNum = getString(getResources().getIdentifier(tableAttribute, "string", "com.example.quentinlautischer.cmput301_assignment1"));
        TextView mTextView = (TextView) rootView.findViewById(getResources().getIdentifier(tableNum, "id", "com.example.quentinlautischer.cmput301_assignment1"));
        mTextView.setText(String.valueOf(value));
    }
}
