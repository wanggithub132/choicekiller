package com.example.wanghanqi.myapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;

/**
 * Created by wanghanqi on 2020/2/22.
 */

@Dao
public interface MyDao {

    //
    @Insert
    void inster(ChoiceBean bean);

    @Delete
    void deletItem(ChoiceBean bean);

    @Update
    void updateItem(ChoiceBean bean);
}
