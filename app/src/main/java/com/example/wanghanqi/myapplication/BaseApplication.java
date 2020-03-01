package com.example.wanghanqi.myapplication;

import android.app.Application;

import com.example.wanghanqi.myapplication.utils.BaseLib;

/**
 * Created by wanghanqi on 2020/2/9.
 */

public class BaseApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        BaseLib.init(this);
    }
}
