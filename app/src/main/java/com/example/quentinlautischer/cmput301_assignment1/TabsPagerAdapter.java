package com.example.quentinlautischer.cmput301_assignment1;

/**
 * Created by quentinlautischer on 2015-09-09.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public  Fragment f1;

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new ReactionTimerFragment();
            case 1:
                // Games fragment activity
                return new BuzzerGameFragment();
            case 2:
                // Movies fragment activity
                Fragment f = new StatsFragment();
                f1 = f;
                return f;
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}