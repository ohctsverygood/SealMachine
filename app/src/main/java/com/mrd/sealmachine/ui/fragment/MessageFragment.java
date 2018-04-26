package com.mrd.sealmachine.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mrd.sealmachine.R;
import com.mrd.sealmachine.base.BaseFragment;
import com.mrd.sealmachine.ui.SealBindActivity;
import com.mrd.sealmachine.ui.SealMessageActivity;
import com.mrd.sealmachine.ui.adapter.SealMessageAdapter;
import com.mrd.sealmachine.ui.bean.Message;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends BaseFragment implements Handler.Callback,View.OnClickListener,XRecyclerView.LoadingListener,SealMessageAdapter.OnItemClickListener{


    public SealMessageAdapter mAdapter;

    public XRecyclerView mRecycler2;
    public List<Message> data;
    private static final String TAG = "fragment.MessageFragment";
    private Handler mHandler;

    public MessageFragment()
    {
        title="历史信息";
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<Message>();
        for(int i=0;i<10;i++) {
//            String title = null;
//            if(i==0||i%2==0)
//                title = "封条机位置信息";
//            else
//                title = "封条号绑定确认";
//            data.add(new Message(title, "2018-4-16 17:50:5" + (10-i)));
            data.add(new Message("封条号绑定信息","2018-4-16 17:50:5" + (10-i)));
        }
        mHandler = new Handler(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"== onCreateView");
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        mAdapter = new SealMessageAdapter(getActivity(),data,R.layout.item_fragme_sealmessage_history);
        mRecycler2 = (XRecyclerView) v.findViewById(R.id.rv_sealmesssage);
        mRecycler2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecycler2.setPullRefreshEnabled(false);
        mRecycler2.setLoadingMoreEnabled(data.size()<15);
        mRecycler2.setLoadingListener(this);
        mRecycler2.setAdapter(mAdapter);
        mRecycler2.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter.setOnClickListener(this);
        initRecyleHeadView(inflater,container);
        mRecycler2.setEmptyView(v.findViewById(R.id.rl_emptyview));
        return v;

    }


    private void initRecyleHeadView(LayoutInflater inflater,ViewGroup container)
    {
        View head = inflater.inflate(R.layout.head_message,container,false);
        Button bt = (Button)head.findViewById(R.id.clear);
        bt.setOnClickListener(this);
        mRecycler2.addHeaderView(head);
    }

    @Override
    public void onItemClick(int position) {
        Message m = data.get(position-1);
//        if(m.getTitle().equals("封条机位置信息"))
//            getActivity().startActivity(new Intent(getActivity(), SealMessageActivity.class));
//        else
            getActivity().startActivity(new Intent(getActivity(), SealBindActivity.class));
    }

        @Override
        public void onRefresh() {}

        @Override
        public void onLoadMore() {
            mHandler.sendEmptyMessageDelayed(1,2000);
        }

    @Override
    public void onClick(View view) {
        showDialog("是否清除封条信息？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                data.clear();
                mAdapter.notifyDataSetChanged();
            }
        });
    }




    @Override
    public boolean handleMessage(android.os.Message message) {
        switch (message.what){
            case 1:
                mRecycler2.loadMoreComplete();

                for(int i=0;i<5;i++) {
                    String title = ((i==0||i%2==0)==true)?"封条机位置信息":"封条号绑定确认";
                    data.add(new Message(title, "2018-4-15:10:3" + (10-i)));
                }
                if(data.size()>15)
                    mRecycler2.setLoadingMoreEnabled(false);
                mAdapter.notifyDataSetChanged();
                break;
        }



        return false;
    }
}
