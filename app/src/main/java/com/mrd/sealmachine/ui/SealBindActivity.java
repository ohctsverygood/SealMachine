package com.mrd.sealmachine.ui;

import com.mrd.sealmachine.R;
import com.mrd.sealmachine.base.MyBaseActivity;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SealBindActivity extends MyBaseActivity {

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_sealbind;
    }

    @Override
    protected void initView() {
        setTitle("封条号绑定信息");
        setBack(true);
    }
}
