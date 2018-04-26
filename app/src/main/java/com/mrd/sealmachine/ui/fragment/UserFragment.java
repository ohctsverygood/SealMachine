package com.mrd.sealmachine.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mrd.sealmachine.R;
import com.mrd.sealmachine.base.BaseFragment;
import com.mrd.sealmachine.data.prefs.UserPrefs;
import com.mrd.sealmachine.ui.LoginActivity;

public class UserFragment extends BaseFragment implements View.OnClickListener{


    Button btDownUp = null;
    TextView tvCarNum = null;
    UserPrefs mPrefs;

    public UserFragment()
    {
        title="用户信息";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = new UserPrefs(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        btDownUp = (Button) v.findViewById(R.id.user_downup);
        tvCarNum = (TextView)v.findViewById(R.id.user_tv_car_number);
        btDownUp.setOnClickListener(this);
        tvCarNum.setText(mPrefs.getDriverNumber());
        return v;
    }


    @Override
    public void onClick(View view) {
        showDialog("是否要退出当前用户?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPrefs.loadUp();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
}
