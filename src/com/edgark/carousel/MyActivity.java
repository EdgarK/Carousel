package com.edgark.carousel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity implements ScrollViewListener, View.OnTouchListener {
    /**
     * Called when the activity is first created.
     */
    private int active_item = 2;
    private int child_count;
    private boolean touched = false;
    private ObservableScrollView scrollView;
    private int left;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        ((ImageView) findViewById(R.id.img1)).setAlpha(50);
//        ((ImageView) findViewById(R.id.img3)).setAlpha(50);
        findViewById(R.id.scroll).setOnTouchListener(this);
//        ((ImageView) findViewById(R.id.img3)).setOnClickListener(this);
        scrollView = ((ObservableScrollView) findViewById(R.id.scroll));
        scrollView.setScrollViewListener(this);
//        ((ObservableScrollView) findViewById(R.id.scroll)).scrollTo(5,5);
        child_count = ((LinearLayout) findViewById(R.id.inscroll)).getChildCount();
        Log.e("sdsdsd", String.valueOf(MotionEvent.ACTION_UP));

    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {


        if (touched) {
            if (x > oldx) {
                Log.e("sdsdsdsdsd", "scrolling left");
                active_item++;
                if (active_item == child_count) {
                    active_item = child_count - 1;
                }
            } else {
                active_item--;
                if (active_item < 0) {
                    active_item = 0;
                }
                Log.e("sdsdsdsdsd", "scrolling right");
            }
            left = ((LinearLayout) findViewById(R.id.inscroll)).getChildAt(active_item).getLeft();
            Log.e("app xxxx", String.valueOf(x));
            Log.e("app", String.valueOf(left));
            Log.e("app", String.valueOf(active_item));
            touched = false;
//        scrollView.scrollTo(left + 50,y);
            scrollView.scrollTo(left, y);
        }else{
            touched = false;
            scrollView.scrollTo(left, y);
        }

    }

    @Override
    public void onScrollEnded() {
        Log.e("<<<<<<<<<<<<<<<<", "sdsddsdsdsdsdsd >>>>>>>>>>>");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            touched = true;
        }
//        Log.e("app ",String.valueOf(motionEvent));
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void run(View view) {
        Log.e("app ", "something strange has happened");
        scrollView.scrollTo(300, 0);
        Intent intent = new Intent(this, ListTestActivity.class);
        startActivity(intent);
    }
}
