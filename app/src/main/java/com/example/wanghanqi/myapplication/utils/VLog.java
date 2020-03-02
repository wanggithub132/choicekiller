package com.example.wanghanqi.myapplication.utils;

import android.util.Log;

/**
 * Created by wanghanqi on 2020/2/7.
 */

public class VLog {
    private static final String TAG = "WHQ_";


    public static void d(String msg){
        d("",msg);
    }

    public static void d(String tag, String msg) {
        Log.d(TAG + tag, msg);
    }
}
