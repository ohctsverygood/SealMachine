package com.mrd.sealmachine.ui.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mrd.sealmachine.R;
import com.mrd.sealmachine.ui.bean.Message;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class SealMessageAdapter extends RecyclerView.Adapter<SealMessageAdapter.MessageHodler> {

    private Context context;
    private List<Message> data;
    private LayoutInflater inflater;
    private OnItemClickListener mListener;
    private int resLayoutId;


    public SealMessageAdapter(Context context, List<Message> data)
    {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    public SealMessageAdapter(Context context, List<Message> data,int resLayoutId)
    {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.resLayoutId =resLayoutId;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public MessageHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        if(resLayoutId==0)
            resLayoutId = R.layout.item_fragme_sealmessage;
        return new MessageHodler(inflater.inflate(resLayoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(MessageHodler holder, int position) {
        holder.build(data.get(position));
    }

    public class MessageHodler extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView title;
        public TextView date;

        public  MessageHodler(View v)
        {
            super(v);
            title = (TextView)v.findViewById(R.id.message_title);
            date  = (TextView)v.findViewById(R.id.message_date);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener!=null)
            mListener.onItemClick(getLayoutPosition()-1); //通过-1获取下标
        }

        public void build(Message message)
        {
            title.setText(message.getTitle());
            date.setText(message.getDate());
        }
    }

    public void setOnClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static interface  OnItemClickListener
    {
        void onItemClick(int position);
    }
}
