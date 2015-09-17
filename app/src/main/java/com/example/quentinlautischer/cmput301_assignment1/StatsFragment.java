package com.example.quentinlautischer.cmput301_assignment1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
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

    private View rootView;

//    private String[] reactStatFields = {  "reactMinTime10", "reactMinTime100", "reactMinTimeAll",
//                                    "reactMaxTime10", "reactMaxTime100", "reactMaxTimeAll",
//                                    "reactAvgTime10", "reactAvgTime100", "reactAvgTimeAll",
//                                    "reactMedTime10", "reactMedTime100", "reactMedTimeAll" };

    private String[] buzzerStatFields = { "b2P_p1", "b2P_p2",
                                    "b3P_p1", "b3P_p2", "b3P_p3",
                                    "b4P_p1", "b4P_p2", "b4P_p3", "b4P_p4" };


    @Override
    public void setMenuVisibility(final boolean visible){
        super.setMenuVisibility(visible);
        if (visible && isResumed()){
            initStats(rootView);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.stats_fragment, container, false);

        this.sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        this.root = (MainActivity) getActivity();
        this.rootView = rootView;
        initStats(rootView);

        rootView.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initStats(rootView);
            }
        });

        rootView.findViewById(R.id.clearStats).getBackground().setColorFilter(0xAAFF0000, PorterDuff.Mode.MULTIPLY);

        rootView.findViewById(R.id.clearStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View thisView = view;
                AlertDialog.Builder alert = new AlertDialog.Builder(root);
                alert.setTitle("Are you sure you want to clear stats?");
                // alert.setMessage("Message");

                alert.setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        root.statsController.clearStats();
                        initStats(thisView);
                    }
                });

                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });

                alert.show();

            }
        });

        return rootView;
    }

    private void clearStats(View rootView){

    }

    private void initStats(View rootView) {
        setStatTableEntity("reactMinTime10", root.statsController.getMinTimeForLast(10));
        setStatTableEntity("reactMinTime100", root.statsController.getMinTimeForLast(100));
        setStatTableEntity("reactMinTimeAll", root.statsController.getMinTimeForLast(Integer.MAX_VALUE));

        setStatTableEntity("reactMaxTime10", root.statsController.getMaxTimeForLast(10));
        setStatTableEntity("reactMaxTime100", root.statsController.getMaxTimeForLast(100));
        setStatTableEntity("reactMaxTimeAll", root.statsController.getMaxTimeForLast(Integer.MAX_VALUE));

        setStatTableEntity("reactAvgTime10", root.statsController.getAvgTimeForLast(10));
        setStatTableEntity("reactAvgTime100", root.statsController.getAvgTimeForLast(100));
        setStatTableEntity("reactAvgTimeAll", root.statsController.getAvgTimeForLast(Integer.MAX_VALUE));

        setStatTableEntity("reactMedTime10", root.statsController.getMedTimeForLast(10));
        setStatTableEntity("reactMedTime100", root.statsController.getMedTimeForLast(100));
        setStatTableEntity("reactMedTimeAll", root.statsController.getMedTimeForLast(Integer.MAX_VALUE));

        for(String key: buzzerStatFields){
            setStatTableEntity(key, root.statsController.getBuzzerClicks(key));
        }
    }


    private void setStatTableEntity(String tableAttribute, Integer value) {
        String tableNum = getString(getResources().getIdentifier(tableAttribute, "string", "com.example.quentinlautischer.cmput301_assignment1"));
        TextView mTextView = (TextView) rootView.findViewById(getResources().getIdentifier(tableNum, "id", "com.example.quentinlautischer.cmput301_assignment1"));
        mTextView.setText(String.valueOf(value));
    }
}
