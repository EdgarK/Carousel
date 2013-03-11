package com.edgark.carousel;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by .
 * User: EdgarK
 * Date: 3/9/13
 * Time: 1:30 PM
 */
public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener {
    private int img_id;
    private boolean selected = false;
    private ImageView iv;
    private int position;
    private AlphaAnimation[] animations = new AlphaAnimation[2];
    private boolean first = false;
    private boolean last = false;
    private boolean clickable = true;
    private int text;
    private TextView tv;
    private ScreenSlidePagerActivity activity;



    public ScreenSlidePageFragment(Integer[] img, int position) {
        initializer(img, position);
    }

    public ScreenSlidePageFragment(int position, String firstOrLast, Integer[] res) {
        initializer(res, position);
        if (firstOrLast.equals("first")) {
            first = true;
        } else {
            last = true;
        }
    }

    private void initializer(Integer[] res, int position){
        this.position = position;
        makeAnimations();
        img_id = res[0];
        this.text = res[1];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        iv = ((ImageView) rootView.findViewById(R.id.iv));
        tv = ((TextView) rootView.findViewById(R.id.text_under_image));

        iv.setImageResource(img_id);
        tv.setText(text);
        iv.setOnClickListener(this);
        if (!selected || first || last)
            iv.setAlpha(100);
            tv.setTextColor(Color.argb(100, 0, 0, 0));
        activity = (ScreenSlidePagerActivity) getActivity();

        return rootView;
    }

    public void setClickable(boolean clickable){
        this.clickable = clickable;
    }

    public void setSelected(boolean selected) {
        if (!first && !last) {
            if (selected) {
                if (iv != null) iv.startAnimation(animations[0]);
            } else {
                if (iv != null) iv.startAnimation(animations[1]);
            }
            this.selected = selected;
        }else{
            if(selected) onClick();
        }
    }

    public void onClick() {
        if (first) activity.selectLast();
        if (last) activity.selectFirst();
        if (!first && !last) activity.select(position);
    }

    @Override
    public void onClick(View view) {
        if(clickable) onClick();
    }
    private void makeAnimations() {
        final Float[][] choises = {{0.5f, 1.0f, 100f, 255f}, {1.0f, 0.5f, 255f, 100f}};

        for (int i = 0; i < 2; i++) {
            AlphaAnimation animation = new AlphaAnimation(choises[i][0], choises[i][1]);
            animation.setDuration(300);
            final int index = i;
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    iv.setAlpha(Math.round(choises[index][2]));
                    tv.setTextColor(Color.argb(Math.round(choises[index][2]), 0, 0, 0));
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    iv.setAlpha(Math.round(choises[index][3]));
                    tv.setTextColor(Color.argb(Math.round(choises[index][3]), 0, 0, 0));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            animations[i] = animation;
        }
    }
}