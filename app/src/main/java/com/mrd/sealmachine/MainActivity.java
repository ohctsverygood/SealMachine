package com.mrd.sealmachine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.mrd.sealmachine.base.BaseActivity;
import com.mrd.sealmachine.base.BaseFragment;
import com.mrd.sealmachine.base.MyBaseActivity;
import com.mrd.sealmachine.ui.adapter.MainFragmentAdapter;
import com.mrd.sealmachine.ui.fragment.MessageFragment;
import com.mrd.sealmachine.ui.fragment.ScanFragment;
import com.mrd.sealmachine.ui.fragment.SealMessageFragment;
import com.mrd.sealmachine.ui.fragment.UserFragment;
import com.mrd.sealmachine.ui.weight.MyCaptureManager;

public class MainActivity extends MyBaseActivity implements BottomNavigationBar.OnTabSelectedListener,ViewPager.OnPageChangeListener,ScanFragment.DecodeResultListener{


    BottomNavigationBar navigationBar;
    ViewPager vp;
    BaseFragment[] fragmentData;
    static final String TAG = "activity.MainActivity";
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 200;

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_main;
    }

    public void initView()
    {
        setTitle("封条管理");
        vp = (ViewPager) findViewById(R.id.main_vp);
        initNavigation();
        ScanFragment scanFragment = new ScanFragment();
        scanFragment.setDecodeResultListener(this);
        fragmentData = new BaseFragment[]{new SealMessageFragment(),scanFragment,new UserFragment()};
        vp.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),fragmentData));
        vp.addOnPageChangeListener(this);
        setCurrentPageIndex(0);
        checkPermission();
    }



    private void initNavigation()
    {
        navigationBar = (BottomNavigationBar)findViewById(R.id.main_bottombar);
        navigationBar.setTabSelectedListener(this);
        navigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        navigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


        BottomNavigationItem mes = new BottomNavigationItem(R.drawable.mes3_blue,"封条信息")
                .setInactiveIconResource(R.drawable.mes3_gray)
                .setActiveColorResource(R.color.tabColor);

        BottomNavigationItem scan = new BottomNavigationItem(R.drawable.scan_blue,"请求封条")
                .setInactiveIconResource(R.drawable.scan_gray)
                .setActiveColorResource(R.color.tabColor);

        BottomNavigationItem use = new BottomNavigationItem(R.drawable.user_blue,"用户")
                .setInactiveIconResource(R.drawable.user_gray)
                .setActiveColorResource(R.color.tabColor);

        navigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(mes)
                .addItem(scan)
                .addItem(use).initialise();
    }

    public void checkPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //当前系统大于等于6.0
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //不具有拍照权限，需要进行权限申请
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA}, MainActivity.REQUEST_PERMISSION_CAMERA_CODE);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION_CAMERA_CODE)
        {
            if (grantResults.length >= 1) {
                int cameraResult = grantResults[0];//相机权限
                boolean cameraGranted = cameraResult == PackageManager.PERMISSION_GRANTED;//拍照权限
                if(!cameraGranted)
                    showDialog("由于权限问题，当前无法进行扫描操作。");

            }
        }
    }

    @Override
    public void onTabReselected(int position) {}

    @Override
    public void onTabSelected(int position) {
        if(vp.getCurrentItem()!=position)
        vp.setCurrentItem(position,false);
        setTitle(fragmentData[position].getTitle());
    }


    @Override
    public void onTabUnselected(int position) { }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onPageSelected(int position) {
        ScanFragment fragment =  (ScanFragment) fragmentData[1];
        if(position==1)
            fragment.onVisibile();
        else
            fragment.onInVisibile();
    }

    @Override
    public void successful(BarcodeResult result) {
    setCurrentPageIndex(0);
    showDialog("请求成功!路径"+result.getText());
    }

    @Override
    public void error() {}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    public void setCurrentPageIndex(int position)
    {
        vp.setCurrentItem(position);
        navigationBar.selectTab(position);
    }
}
