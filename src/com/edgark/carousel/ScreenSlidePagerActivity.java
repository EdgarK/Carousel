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
 * Created with IntelliJ IDEA.
 * User: edgar
 * Date: 3/9/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
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


    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = pics.length + 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_pager);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().widthPixels / -1.52f));
        mPager.setOffscreenPageLimit(2);
        mPager.setCurrentItem(selected);
        mPager.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPageSelected(int i) {
        mPagerAdapter.getPage(selected).setSelected(false);
        mPagerAdapter.getPage(i).setSelected(true);
        selected = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
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
            Log.e("getItem",String.format("creation of item number %d has began",position));
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
    public void run(View view){}

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
}