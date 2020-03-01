package com.example.wanghanqi.myapplication.utils;

import android.content.Context;

/**
 * Created by wanghanqi on 2020/2/9.
 */

public class BaseLib {
    private static Context mContext;
    public static void init(Context contect){
        mContext= contect.getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
