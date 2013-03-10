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

    private int leftImg;
    private int rightImg;

    private ImageView leftIv;
    private ImageView rightIv;


    public ScreenSlidePageFragment(int img, int position){
      this.position = position;
      img_id = img;
      makeAnimations();
    }
    private void makeAnimations(){
        final Float[][] choises = {{0.5f,1.0f,100f,255f},{1.0f,0.5f,255f,100f}};

        for(int i=0;i<2;i++){
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
        leftIv = ((ImageView) rootView.findViewById(R.id.left_iv));
        rightIv = ((ImageView) rootView.findViewById(R.id.right_iv));
        iv.setImageResource(img_id);
        iv.setOnClickListener(this);
        if(!selected)
            iv.setAlpha(100);

        if ((Integer) leftImg != null) {
            leftIv.setImageResource(leftImg);
            leftIv.setAlpha(100);
            leftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ScreenSlidePagerActivity) getActivity()).selectLast();
                }
            });
        }
        if ((Integer) rightImg != null) {
            rightIv.setImageResource(rightImg);
            rightIv.setAlpha(100);
            rightIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ScreenSlidePagerActivity) getActivity()).selectFirst();
                }
            });
        }
        return rootView;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        if(selected){
            if (iv != null) {
                iv.startAnimation(animations[0]);
            }
        }else{
            if (iv != null) {
                iv.startAnimation(animations[1]);
            }
        }
        this.selected = selected;
    }

    public void setLeftImg(int img){
        this.leftImg = img;

    }

    public void setRightImg(int img){
        this.rightImg = img;
    }


    @Override
    public void onClick(View view) {
        ((ScreenSlidePagerActivity) getActivity()).select(position);
    }
}