package com.mrd.sealmachine.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mrd.sealmachine.base.BaseFragment;

/**
 * Created by Administrator on 2018/4/2.
 */

public class SealMessageFragmentAdapter extends FragmentPagerAdapter{

    BaseFragment[] fragemnts;
    private static final String TAG = "adapter.MainFragmentAdapter";
    public SealMessageFragmentAdapter(FragmentManager manager, BaseFragment[] data)
    {
        super(manager);
        fragemnts = data;
    }

    @Override
    public int getCount() {
        return fragemnts.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       return super.instantiateItem(container,position);
    }


        @Override
    public Fragment getItem(int position) {
        Log.d(TAG,"== getItem");
        return fragemnts[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragemnts[position].getTitle();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
