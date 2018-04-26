package com.mrd.sealmachine.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrd.sealmachine.MainActivity;
import com.mrd.sealmachine.R;
import com.mrd.sealmachine.data.prefs.UserPrefs;
import com.mrd.sealmachine.data.utils.KeyboardUtil;

/**
 * Created by Administrator on 2018/4/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,Handler.Callback{


    UserPrefs mUserPrefs = null;
    EditText etPhoneNumber;
    EditText etDriverNumber;
    EditText etMes;
    Button btSendMessage;
    Button btLogin;
    private String phoneNumber;
    private String carNumber;
    private final int SEND_VERIFICATION_CODE = 1;
    private Handler handler;
    private KeyboardUtil keyboardUtil;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserPrefs = new UserPrefs(this);
        if(mUserPrefs.getPhone()!=null)
        {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.activity_login);
        context = this;
        handler = new Handler(this);
        initView();
    }

    public void initView()
    {
        etDriverNumber = (EditText)findViewById(R.id.login_etcarnumber);
        etPhoneNumber = (EditText)findViewById(R.id.login_etphonenumber);
        etMes = (EditText) findViewById(R.id.login_etcode);
        btSendMessage = (Button) findViewById(R.id.login_btsendmes);
        btLogin = (Button) findViewById(R.id.login_btsubmit);
        initKey();
        btSendMessage.setOnClickListener(this);
        btLogin.setOnClickListener(this);

    }


    private void initKey()
    {
        etDriverNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    InputMethodManager inputMethodManager = (InputMethodManager) context
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
                    keyboardUtil = new KeyboardUtil(LoginActivity.this, etDriverNumber);
                    keyboardUtil.hideSoftInputMethod();
                    keyboardUtil.showKeyboard();
                }else
                {
                    keyboardUtil.hideKeyboard();
                }
            }
        });

        etDriverNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!keyboardUtil.isShow())
                keyboardUtil.showKeyboard();
            }
        });
    }

    @Override
    public boolean handleMessage(Message message) {

        switch (message.what)
        {
            case  SEND_VERIFICATION_CODE:
                Integer num = message.arg1;
                if(num==0)
                {
                    btSendMessage.setEnabled(true);
                    btSendMessage.setText("发送验证码");
                }else
                {
                    int m = --num;
                    sendVerification(m);
                    btSendMessage.setText( "重新发送:"+m);
                }
                break;


        }

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_btsubmit:
                login();
                break;

            case R.id.login_btsendmes:
                sendMes();
                break;
        }

    }


    private void login()
    {

        if(phoneNumber==null)
        {
            showToast("请发送验证码");
            return;
        }

        carNumber= etDriverNumber.getText().toString();
        if(!checkCarNum(carNumber))
        {
            showToast("请输入合法的车牌号码");
            carNumber=null;
            return;
        }

        String code = etMes.getText().toString();
        if(!checkVerificationCode(code))
        {
            showToast("请输入合法的验证码");
            return ;
        }


        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        mUserPrefs.setPhone(phoneNumber);
        mUserPrefs.setDriverNumber(carNumber);
        finish();


    }

    private void sendMes()
    {
        phoneNumber = etPhoneNumber.getText().toString();
        if(!checkPhoneNum(phoneNumber))
        {
            showToast("请输入合法的手机号");
            phoneNumber=null;
            return ;
        }

        showToast("验证码:  11111");
        sendVerification(60);

    }


    private void sendVerification(int mm)
    {
        btSendMessage.setEnabled(false);
        Message msg = Message.obtain();
        msg.what = SEND_VERIFICATION_CODE;
        msg.arg1=mm;
        handler.sendMessageDelayed(msg,1000)
                ;
    }

    private boolean checkCarNum(String str)
    {
        if(str!=null&&str.length()!=7)
            return false;

        return true;
    }


    private boolean checkPhoneNum(String str)
    {
        if(str!=null&&str.length()!=11)
            return false;

        return true;
    }

    private boolean checkVerificationCode(String str)
    {
        if(str==null||str.length()!=5||!str.equals("11111"))
            return false;

        return true;


    }


    private void showToast(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }

        return super.onTouchEvent(event);
    }

}
