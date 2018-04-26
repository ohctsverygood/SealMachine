package com.mrd.sealmachine.ui.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by Administrator on 2018/4/3.
 */

public class MyViewPager extends ViewPager {

    public MyViewPager(Context context)
    {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet set)
    {
        super(context,set);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
       return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
