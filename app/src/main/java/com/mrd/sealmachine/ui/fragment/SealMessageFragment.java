package com.mrd.sealmachine.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrd.sealmachine.R;
import com.mrd.sealmachine.base.BaseFragment;
import com.mrd.sealmachine.ui.adapter.MainFragmentAdapter;
import com.mrd.sealmachine.ui.adapter.SealMessageFragmentAdapter;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SealMessageFragment extends BaseFragment {


    TabLayout tbLayout= null;
    ViewPager viewPager= null;
    MessageFragment mf = null;
    NewMessageFragment nf = null;
    BaseFragment[] fragments = new BaseFragment[2];
    SealMessageFragmentAdapter mAdapter = null;
    View mContainer = null;
    private static final String TAG = "fragment.SealMessageFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"== onCreate");
        mf = new MessageFragment();
        nf = new NewMessageFragment();
        fragments[0] = nf;
        fragments[1] = mf;
        mAdapter = new SealMessageFragmentAdapter(getFragmentManager(), fragments);
//        mAdapter = new SealMessageFragmentAdapter(getFragmentManager(),fragments);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"== onCreateView");
        if(mContainer==null) {
            mContainer = inflater.inflate(R.layout.fragment_message_pager, container, false);
            viewPager = (ViewPager) mContainer.findViewById(R.id.vp_message_pager);
            tbLayout = (TabLayout) mContainer.findViewById(R.id.tb_message_tab);
            viewPager.setAdapter(mAdapter);
            tbLayout.setupWithViewPager(viewPager);
        }

        return mContainer;
    }



    @Override
    public String getTitle() {
        return "封条信息";
    }
}
