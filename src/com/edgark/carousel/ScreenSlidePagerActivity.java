package com.edgark.carousel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by.
 * User: EdgarK
 * Date: 3/9/13
 * Time: 1:35 PM
 */
public class ScreenSlidePagerActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    static Integer[][] pics = {
            {R.drawable.icon_espresso,R.string.pict1},
            {R.drawable.icon_aeropress,R.string.pict2},
            {R.drawable.icon_maker,R.string.pict3},
            {R.drawable.icon_aeropress,R.string.pict4},
            {R.drawable.icon_espresso,R.string.pict5},
            {R.drawable.icon_maker,R.string.pict6}
    };

    private int selected = 1;
    private static final int NUM_PAGES = pics.length + 2;
    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        if(i == 0) i = NUM_PAGES -2;
        if(i == NUM_PAGES - 1) i = 1;
        selected = i;
        Log.e("onPageSelected",String.format("settint selected to %d",i));
    }



    public void select(int position){
        select(position, true);
    }
    public void select(int position, boolean animation){
        if(position != selected){
            Log.e("select", String.valueOf(position));
            mPager.setCurrentItem(position, animation);
            onPageSelected(position);
        }else {
            Log.e("select", String.format("Select trigered on selected item number %d",position));
        }

    }
    public void selectLast(){
        select(NUM_PAGES - 2, false);
    }
    public void selectFirst(){
        select(1, false);
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
            if (pages[position] != null) {
                return pages[position];
            }
            if(position != 0 && position != NUM_PAGES - 1) pages[position] = new ScreenSlidePageFragment(pics[position - 1], position);
            if(position == 0){
                pages[position] = new ScreenSlidePageFragment(position, "first", pics[pics.length - 1]);
            }
            if(position == NUM_PAGES - 1){
                pages[position] = new ScreenSlidePageFragment(position, "last", pics[0]);
            }
            if(position == selected) {
                pages[position].setSelected(true);
            }
            return pages[position];
        }
    }


    @Override
    public void onPageScrollStateChanged(int i) {

    }
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
}