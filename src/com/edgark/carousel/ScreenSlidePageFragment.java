package com.edgark.carousel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created with IntelliJ IDEA.
 * User: edgar
 * Date: 3/9/13
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener {
    private int img_id;
    private boolean selected = false;
    private ImageView iv;
    private int position;
    private AlphaAnimation[] animations = new AlphaAnimation[2];

    private boolean first = false;
    private boolean last = false;
    private int text;
    private TextView tv;

    private ScreenSlidePagerActivity activity;


    private void initializer(Integer[] res, int position){
        this.position = position;
        makeAnimations();
        img_id = res[0];
        this.text = res[1];
    }
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
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    iv.setAlpha(Math.round(choises[index][3]));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            });
            animations[i] = animation;
        }

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
        if (!selected)
            iv.setAlpha(100);
//            tv.setAlpha(100);
        activity = (ScreenSlidePagerActivity) getActivity();

        return rootView;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        if (!first && !last) {
            if (selected) {
                if (iv != null) {
                    iv.startAnimation(animations[0]);
                }
            } else {
                if (iv != null) {
                    iv.startAnimation(animations[1]);
                }
            }
            this.selected = selected;
        }else{
            if(selected) onClick();
        }
    }


    @Override
    public void onClick(View view) {
        onClick();
    }

    public void onClick() {
        if (first) activity.selectLast();
        if (last) activity.selectFirst();
        if (!first && !last) activity.select(position);
    }
}