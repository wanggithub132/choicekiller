package com.example.wanghanqi.myapplication.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghanqi on 2020/2/22.
 */

public class MyConverter {

    @TypeConverter
    public static List<String> fromString(String value){
        Type listType = new TypeToken<List<String>>(){}.getType();
        return new Gson().fromJson(value,listType);
    }

    @TypeConverter
    public static String fromArrayList(List<String> list){
        return new Gson().toJson(list);
    }

}
