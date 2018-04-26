package com.mrd.sealmachine.ui;

import com.mrd.sealmachine.R;
import com.mrd.sealmachine.base.MyBaseActivity;

/**
 * Created by Administrator on 2018/4/18.
 */

public class SealMessageActivity extends MyBaseActivity {

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_sealinfo;
    }

    @Override
    protected void initView() {
        setBack(true);
        setTitle("待领取封条信息");
    }
}
