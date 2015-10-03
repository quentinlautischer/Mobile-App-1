package com.example.quentinlautischer.cmput301_assignment1;
/**
 * Created by quentinlautischer on 2015-09-09.
 * http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/\
 * https://github.com/codepath/android_guides/wiki/Google-Play-Style-Tabs-using-TabLayout
 */

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    public ViewPager viewPager;
    public TabsPagerAdapter mAdapter;
    private ActionBar actionBar;

    public StatsModel statsModel;

    private String[] tabs = { "Reaction Time", "Buzzer Game", "Stats" };

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

        statsModel = StatsModel.getInstance(this);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // on changing the page make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {}

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        //on tab selected show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {}

    @Override
    public void onBackPressed(){
        if ( findViewById(R.id.buzzer_setup_layout).getVisibility() == View.INVISIBLE ){
            LinearLayout linear = (LinearLayout) findViewById(R.id.buzzer_button_layout);

            findViewById(R.id.buzzer_setup_layout).setVisibility(View.VISIBLE);
            linear.removeAllViews();
        } else{
            super.onBackPressed();
        }
    }

    public void startEmail(){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Reaction/Buzzer Stats");
        intent.putExtra(Intent.EXTRA_TEXT,statsModel.getStatsDataPrinted());
        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException e){
            Toast.makeText(this, "There is no email client Installed.", Toast.LENGTH_SHORT).show();
        }
    }

}