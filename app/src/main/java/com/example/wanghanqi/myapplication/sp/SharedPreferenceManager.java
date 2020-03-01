package com.example.wanghanqi.myapplication.sp;

import com.example.wanghanqi.myapplication.utils.BaseLib;

/**
 * Created by wanghanqi on 2020/2/9.
 * sp存储管理单例
 */
public class SharedPreferenceManager {

    private static volatile SharedPreferenceManager sInstance;
    private static final String FILE_NAME = "WHQ_SP";

    public static synchronized SharedPreferenceManager getsInstance() {
        if (sInstance == null) {
            sInstance = new SharedPreferenceManager();
        }
        return sInstance;
    }


    private BaseSharedPreferences mBaseSharedPreferences;

    private SharedPreferenceManager() {
        mBaseSharedPreferences = new BaseSharedPreferences();
        mBaseSharedPreferences.init(BaseLib.getContext(), FILE_NAME);
    }


    public String getString(String key){
        return  mBaseSharedPreferences.getString(key,"");
    }

    public void pusString(String key,String value){
        mBaseSharedPreferences.putString(key,"");
    }

}
