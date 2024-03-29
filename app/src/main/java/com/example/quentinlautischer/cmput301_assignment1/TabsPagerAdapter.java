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

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new ReactionTimerFragment();
            case 1:
                return new BuzzerGameFragment();
            case 2:
                return new StatsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}