package com.example.wanghanqi.myapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.utils.BaseLib;

/**
 * Created by wanghanqi on 2020/2/22.
 */
@Database(entities = {ChoiceBean.class}, exportSchema = false,version = DbConstance.VERSION)
public abstract class MyDb extends RoomDatabase {
    private static volatile MyDb sInstance;

    public static synchronized MyDb getsInstance() {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(BaseLib.getContext(), MyDb.class, DbConstance.NAME).build();
        }
        return sInstance;
    }

    public abstract MyDao getMyDao();

}
