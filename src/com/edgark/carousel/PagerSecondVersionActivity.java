package com.edgark.carousel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * Created by.
 * User: EdgarK
 * Date: 3/11/13
 * Time: 10:09 AM
 */
public class PagerSecondVersionActivity extends ScreenSlidePagerActivity implements ViewPager.OnPageChangeListener {
    static Integer[][] pics = {
            {R.drawable.icon_espresso,R.string.pict1},
            {R.drawable.icon_aeropress,R.string.pict2},
            {R.drawable.icon_maker,R.string.pict3},
    };

    private int selected = 1;
    private static final int NUM_PAGES = pics.length;
    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_pager);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().widthPixels / -1.52f));
        mPager.setOffscreenPageLimit(2);
        mPager.setCurrentItem(selected);
        mPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int i) {
        mPagerAdapter.getPage(selected).setSelected(false);
        mPagerAdapter.getPage(i).setSelected(true);
        selected = i;
        Log.e("PagerSecondVersionActivity",String.format("Page vith number %d was selected", i));
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ScreenSlidePageFragment[] pages = new ScreenSlidePageFragment[NUM_PAGES];
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        public ScreenSlidePageFragment getPage(int position){
            return pages[position];
        }

        @Override
        public Fragment getItem(int position) {
            if (pages[position] != null) return pages[position];
            pages[position] = new ScreenSlidePageFragment(pics[position], position);
            pages[position].setClickable(false);
            if(position == selected) pages[position].setSelected(true);
            return pages[position];
        }
    }


}