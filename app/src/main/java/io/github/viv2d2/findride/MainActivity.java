package io.github.viv2d2.findride;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v7.app.AppCompatActivity;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.*;


/**
 * Main screen with tabs.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Initializing tab and pager views
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // Making new tabs and adding to tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("All Rides"));
        tabLayout.addTab(tabLayout.newTab().setText("My Rides"));

        // Adding fragments to a list
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, AllRidesFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, MyRidesFragment.class.getName()));

        // Attaching fragments into tabLayout with ViewPager
        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);

    }

    public void findRide(View view) {
        Intent intent = new Intent(MainActivity.this, FindRideActivity.class);
        startActivity(intent);
    }

    private class SectionPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public SectionPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "All Rides";
                case 1:
                default:
                    return "My Rides";
            }
        }
    }
}
