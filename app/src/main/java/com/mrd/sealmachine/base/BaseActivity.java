package com.mrd.sealmachine.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mrd.sealmachine.R;


public abstract class BaseActivity extends AppCompatActivity {


    Toolbar mToolbar = null;

    ViewGroup mContentView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_base);
        initToolbar();
        int layoutResID = getLayoutId();
        if(layoutResID==0) return ;
        mContentView = (ViewGroup)findViewById(R.id.contentview);
        View v = LayoutInflater.from(this).inflate(layoutResID,mContentView,false);
        mContentView.addView(v);
        initEvent();

    }


    protected  abstract int getLayoutId();

    protected  abstract void initEvent();

    private void initToolbar()
    {
        Toolbar tb = (Toolbar) findViewById(R.id.base_toolber);
        if(tb==null) return;
        mToolbar = tb;
        setSupportActionBar(tb);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    protected  void setTitle(String title)
    {
        if(mToolbar!=null)
        {
            mToolbar.setTitle(title);
        }
    }


    protected  void setBack()
    {
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}
