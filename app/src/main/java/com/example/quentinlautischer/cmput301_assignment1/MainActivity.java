package com.example.quentinlautischer.cmput301_assignment1;
/**
 * Created by quentinlautischer on 2015-09-09.
 * http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/
 */

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.media.*;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Reaction Time", "Buzzer Game", "Stats" };

    private Boolean awaitingClick = false;
    private ReactionTimer reactionTimer = new ReactionTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    public void ReactionTimerClick(View rootView) {

        if (!awaitingClick){
            //Start and wait for Reaction
            findViewById(R.id.reactionTimerRoot).setBackgroundColor(Color.parseColor("#5edf74"));
            findViewById(R.id.reactionTimerTextView).setVisibility(View.INVISIBLE);
            findViewById(R.id.reactionTimerAlert).setVisibility(View.INVISIBLE);

            Random r = new Random();
            int alertDelayTime = r.nextInt(2000) + 200;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.reactionTimerAlert).setVisibility(View.VISIBLE);
                    findViewById(R.id.reactionTimerRoot).setBackgroundColor(Color.parseColor("#CC0000"));
                    awaitingClick = true;
                    reactionTimer.start();
                }
            }, alertDelayTime);
        } else {
            //Reaction
            long time = reactionTimer.stop();
            awaitingClick = false;

            final TextView mTextView = (TextView) findViewById(R.id.reactionTimerTextView);
            mTextView.setText("Tap to begin again \n Your time was: ");
            mTextView.append(String.valueOf(time) + "ms");

            findViewById(R.id.reactionTimerRoot).setBackgroundColor(Color.parseColor("#5edf74"));
            findViewById(R.id.reactionTimerTextView).setVisibility(View.VISIBLE);
            findViewById(R.id.reactionTimerAlert).setVisibility(View.INVISIBLE);


        }

    }


}