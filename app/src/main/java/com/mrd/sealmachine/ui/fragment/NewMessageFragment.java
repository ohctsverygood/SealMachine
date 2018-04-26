package com.mrd.sealmachine.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrd.sealmachine.R;
import com.mrd.sealmachine.base.BaseFragment;
import com.mrd.sealmachine.ui.SealBindActivity;
import com.mrd.sealmachine.ui.SealMessageActivity;
import com.mrd.sealmachine.ui.adapter.MainFragmentAdapter;
import com.mrd.sealmachine.ui.adapter.SealMessageAdapter;
import com.mrd.sealmachine.ui.bean.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class NewMessageFragment extends BaseFragment implements SealMessageAdapter.OnItemClickListener{


    XRecyclerView mRecyclerView = null;
    SealMessageAdapter mAdapter = null;
    List<Message> data = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<Message>();
        for(int i=0;i<2;i++)
            data.add(new Message("待领取封条信息","2018-4-19 15:01:"+(10*(i+1))));

        mAdapter = new SealMessageAdapter(getActivity(),data);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_message, container, false);
        mRecyclerView = (XRecyclerView)v.findViewById(R.id.rv_newmessage_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        mRecyclerView.setEmptyView(v.findViewById(R.id.rl_emptyview));
        return v;
    }

    @Override
    public void onItemClick(int position) {
//        showToast("current "+position);
//        Message m = data.get(position);
//        if(m.getTitle().equals("封条机位置信息"))
            getActivity().startActivity(new Intent(getActivity(), SealMessageActivity.class));
//        else
//            getActivity().startActivity(new Intent(getActivity(), SealBindActivity.class));
    }

    @Override
    public String getTitle() {
        return "最新信息";
    }
}
