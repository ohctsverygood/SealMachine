package com.mrd.sealmachine.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/4/17.
 */

public class UserPrefs {

    private SharedPreferences sp = null;
    private SharedPreferences.Editor editor = null;

    public UserPrefs(Context context)
    {
        sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        editor=sp.edit() ;
    }


    public String getPhone()
    {
        return sp.getString("phone",null);
    }

    public void setPhone(String phone) {
        editor.putString("phone",phone);
        editor.commit();
    }

    public String getDriverNumber() {
       return sp.getString("driverNumber",null);
    }

    public void setDriverNumber(String driverNumber) {
        editor.putString("driverNumber",driverNumber);
        editor.commit();
    }

    public void loadUp()
    {
        editor.putString("driverNumber",null);
        editor.putString("phone",null);
        editor.commit();
    }

}
