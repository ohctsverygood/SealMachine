package com.mrd.sealmachine.base;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.mrd.sealmachine.R;


public abstract class MyBaseActivity extends AppCompatActivity {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(getLayoutResourceID());
        initView();
        initDialog();

    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }


    protected abstract void initView();
    protected abstract  int getLayoutResourceID();

    private void initToolbar()
    {
        Toolbar tb = (Toolbar) findViewById(R.id.base_toolber);
        setSupportActionBar(tb);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    protected  void setTitle(String title)
    {
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(title);

    }


    protected  void setBack(boolean isBack)
    {
        getSupportActionBar().setHomeButtonEnabled(isBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public  void initDialog()
    {
       dialog = new AlertDialog.Builder(this).setTitle("提示").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
            }
        }).create();
    }

    protected void showDialog(String str)
    {
        dialog.setMessage(str);
        dialog.show();
    }

    protected void  showToast(String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    protected void closeDialog()
    {
        if(dialog.isShowing())
            dialog.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDialog();
    }


}
