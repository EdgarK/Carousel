package com.edgark.carousel;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: edgar
 * Date: 3/6/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListTestActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
//    public int selected;


    private ImageAdapter adapter;
    CustomGallery ga;
    Integer[] pics = {
            R.drawable.icon_espresso,
            R.drawable.icon_aeropress,
            R.drawable.icon_maker,
            R.drawable.icon_aeropress,
            R.drawable.icon_espresso,
            R.drawable.icon_maker
    };

//    private final class myArrayAdapter<T> extends ArrayAdapter<T> {
//
//        public myArrayAdapter(Context context, int textViewResourceId, List<T> values) {
//            super(context, textViewResourceId, values);
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View row = convertView;
//            if (row == null) {
//                LayoutInflater inflater = getLayoutInflater();
//
//                row = inflater.inflate(R.layout.item, parent, false);
//            }
//            TextView label = (TextView) row.findViewById(R.id.list_entry);
//            label.setText(getItem(position).toString());
//
//            return (row);
//        }
//    }

    public void run(View view){
        Intent intent = new Intent(this, ScreenSlidePagerActivity.class);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_test);

        ga = (CustomGallery)findViewById(R.id.list);
        adapter = new ImageAdapter(this);
        adapter.setDataset(pics);
        ga.setAdapter(adapter);
        ga.setOnItemClickListener(this);
        ga.setOnItemSelectedListener(this);
        adapter.selected = 0;

        ga.setSelection(1, false);
        select(adapter.getViewAt(1),1);
//        ga.setSelection(1,false);
//        adapter.notifyDataSetChanged();

//        ga.setSelection(pics.length+1,false);
//        ga.setSelection(1,false);
//        ga.setUnselectedAlpha((float) 0.5);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        select(view,i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        select(view,i);
    }

    public void select(View view, int i){
        if(i != 0 && i != pics.length+1){
            int prev = adapter.selected;
            adapter.selected = i;
            ImageView view1 = adapter.getViewAt(prev);
            ga.startAnimation(255, 100, view1);
            ga.startAnimation(100,255,(ImageView) view);
        }else{
            if(i == 0){
                ga.setSelection(pics.length,false);
                select(view,pics.length);
            }else{
                ga.setSelection(1,false);
                select(view,1);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
//        ga.get
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public class ImageAdapter extends BaseAdapter {


        private Context ctx;
        int imageBackground;
        public int selected = 0;

        private ImageView[] views;

        Integer[] dataset;

        public void setDataset(Integer[] pics){
//            selected = 1;
            dataset = new Integer[pics.length + 2];
            dataset[0] = pics[pics.length - 1];
            dataset[pics.length + 1] = pics[0];
            for(int i = 0;i < pics.length;i++){
              dataset[i+1] = pics[i];
            }
            notifyDataSetChanged();
        }

        public ImageView getViewAt(int i){
            return views[i];
        }

        public ImageAdapter(Context c) {
            ctx = c;
            TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
            imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
            ta.recycle();
        }

        @Override
        public int getCount() {
            views = new ImageView[dataset.length];
            return dataset.length;
        }


        @Override
        public Object getItem(int arg0) {
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }


        @Override
        public View getView(int i, View arg1, ViewGroup arg2) {
            ImageView iv;
            iv = views[i];
            if (iv == null) {
                iv = new ImageView(ctx);
                iv.setImageResource(dataset[i]);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(new Gallery.LayoutParams(120,120));
            }
            if(i != selected)
                iv.setAlpha(100);
            if(views[0] != null)
                views[0].setAlpha(100);
            if (views[views.length-1] != null)
                views[views.length-1].setAlpha(100);
            views[i] = iv;
            return iv;
        }

    }
}
