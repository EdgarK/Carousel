package com.edgark.carousel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: edgar
 * Date: 3/8/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomGallery extends Gallery {


    public CustomGallery(Context context) {
        super(context);
    }

    public CustomGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2){
        return e2.getX() > e1.getX();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        int kEvent;
        if(isScrollingLeft(e1, e2)){ //Check if scrolling left
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        }
        else{ //Otherwise scrolling right
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(kEvent, null);
        return true;
    }

    public void startAnimation(final int start,final int end,final ImageView target){
        Animation animation;
        if(start > end){
            animation = new AlphaAnimation(0.5f, 1);
        }else {
            animation = new AlphaAnimation(1, 0.5f);
        }
        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                target.setAlpha(start);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                target.setAlpha(end);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        if(target != null)
            target.startAnimation(animation);
    }


}
