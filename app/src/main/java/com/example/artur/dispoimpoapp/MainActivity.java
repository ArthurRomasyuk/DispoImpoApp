package com.example.artur.dispoimpoapp;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.artur.dispoimpoapp.fragments.ConfigFragment;
import com.example.artur.dispoimpoapp.fragments.DispoFragment;
import com.example.artur.dispoimpoapp.fragments.EventsFragment;
import com.example.artur.dispoimpoapp.fragments.ExitFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        long a = 1526417999000L; //Sun Oct 22 13:43:02 EDT 2017
//        if(System.currentTimeMillis() > a)
//            finish();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
//        getSupportActionBar().setIcon(R.drawable.dispoimpo);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        NonSwipeableViewPager viewPager = findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter =
                new PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_exit_to_app_white_18dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_schedule_white_18dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_settings_white_18dp);
    }

    class PagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[]{"DISPO", "", "", ""};
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new DispoFragment();
                case 1:
                    return new ExitFragment();
                case 2:
                    return new EventsFragment();
                case 3:
                    return new ConfigFragment();
            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View tab = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
//            if(position==0)
//            tv.setText("DISPO");
            return tab;
        }
    }

    public static class Utils {
        static boolean updateEventsFragment;
        static boolean updateDispoFragment;

        public static boolean isUpdateEventsFragment() {
            return updateEventsFragment;
        }

        public static void setUpdateEventsFragment(boolean updateEventsFragment) {
            Utils.updateEventsFragment = updateEventsFragment;
        }

        public static boolean isUpdateDispoFragment() {
            return updateDispoFragment;
        }

        public static void setUpdateDispoFragment(boolean updateDispoFragment) {
            Utils.updateDispoFragment = updateDispoFragment;
        }
    }

}
