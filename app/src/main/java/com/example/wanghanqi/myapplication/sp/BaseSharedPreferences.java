package com.example.wanghanqi.myapplication.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class BaseSharedPreferences {

    private SharedPreferences mSharedPreferences;

    public void init(Context context,String fileName){
        mSharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }

    public void putString(String key,String value){
        mSharedPreferences.edit().putString(key,value).apply();
    }

    public String getString(String key,String defValue){
       return mSharedPreferences.getString(key,defValue);
    }

}
