package com.mrd.sealmachine.base;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mrd.sealmachine.R;

public class BaseFragment extends Fragment {


    public String title;

    public BaseFragment() {

    }


    public String getTitle()
    {
        return title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }


    public void showDialog(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setPositiveButton("确定",null).setMessage(msg);

        builder.create().show();
    }

    public void showDialog(String msg,DialogInterface.OnClickListener positiveListener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setPositiveButton("确定",positiveListener)
                .setMessage(msg)
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        builder.create().show();
    }


    public void showToast(String str)
    {
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }


}
