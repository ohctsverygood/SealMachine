package com.mrd.sealmachine.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mrd.sealmachine.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter{

    BaseFragment[] fragemnts;
    private static final String TAG = "adapter.MainFragmentAdapter";
    public MainFragmentAdapter(FragmentManager manager,BaseFragment[] data)
    {
        super(manager);
        fragemnts = data;
    }

    @Override
    public int getCount() {
        return fragemnts.length;
    }


        @Override
    public Fragment getItem(int position) {
        return fragemnts[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragemnts[position].getTitle();
    }
}
