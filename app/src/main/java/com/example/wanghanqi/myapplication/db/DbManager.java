package com.example.wanghanqi.myapplication.db;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;

import java.util.List;

/**
 * 数据库管理类
 */
public class DbManager {
    private MyDao mDao = MyDb.getsInstance().getMyDao();
    private static volatile DbManager sInstance;
    public static synchronized DbManager getsInstance() {
        if (sInstance == null) {
            sInstance = new DbManager();
        }
        return sInstance;
    }

    public void inster(ChoiceBean bean) {
        bean.setEndTime(System.currentTimeMillis());
        mDao.inster(bean);
    }

    public void deletItem(ChoiceBean bean) {
        bean.setEndTime(System.currentTimeMillis());
        mDao.deletItem(bean);
    }

    public void updateItem(ChoiceBean bean) {
        bean.setEndTime(System.currentTimeMillis());
        mDao.updateItem(bean);
    }

    public ChoiceBean findBeanById(String idkey) {
        return mDao.findBeanById(idkey);
    }

    public List<ChoiceBean> findHistoryBean() {
        return mDao.findHistoryBean();
    }
}
