package com.mrd.sealmachine.data.utils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/17.
 */

public class CarVerificationUtil {
    static final ArrayList<String> carHeads = new ArrayList<String>();

    static {
        carHeads.add("京");
        carHeads.add("津");
        carHeads.add("冀");
        carHeads.add("晋");
        carHeads.add("蒙");
        carHeads.add("辽");
        carHeads.add("吉");
        carHeads.add("黑");
        carHeads.add("沪");
        carHeads.add("苏");
        carHeads.add("浙");
        carHeads.add("皖");
        carHeads.add("闽");
        carHeads.add("赣");
        carHeads.add("鲁");
        carHeads.add("豫");
        carHeads.add("鄂");
        carHeads.add("湘");
        carHeads.add("粤");
        carHeads.add("桂");
        carHeads.add("琼");
        carHeads.add("渝");
        carHeads.add("川");
        carHeads.add("黔");
        carHeads.add("滇");
        carHeads.add("藏");
        carHeads.add("陕");
        carHeads.add("甘");
        carHeads.add("青");
        carHeads.add("宁");
        carHeads.add("新");
        carHeads.add("台");
        carHeads.add("港");
        carHeads.add("澳");
    }

    public boolean isProvince(String s)
    {
        return (carHeads.contains(s));
    }


}
