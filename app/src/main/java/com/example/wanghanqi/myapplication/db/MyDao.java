package com.example.wanghanqi.myapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;

import java.util.List;

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



    //根据title查找
    @Query("select * from whq_table where title = :idkey")
    ChoiceBean findBeanById(String idkey);

    //查找历史记录信息
    @Query("select * from whq_table where type = 1")
    List<ChoiceBean> findHistoryBean();

    //查找内置数据
    @Query("select * from whq_table where type = 0")
    List<ChoiceBean> findOriginalBean();

//    //查找最近一次改动过的数据
    @Query("select * from whq_table order by  end_time desc limit 1 offset 0")
    ChoiceBean findLatestBean();
    //删除仅保留10条id最大的内容
    @Query("delete from whq_table where id not in (select id from whq_table order by id desc limit 10) ")
    void deleteWhenInsert();
}
