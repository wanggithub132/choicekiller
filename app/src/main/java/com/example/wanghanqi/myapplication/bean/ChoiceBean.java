package com.example.wanghanqi.myapplication.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.text.TextUtils;

import com.example.wanghanqi.myapplication.db.DbConstance;
import com.example.wanghanqi.myapplication.db.MyConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghanqi on 2020/2/6.
 * 多选转盘bean类
 */
@Entity(tableName = DbConstance.TABLE_NAME)
public class ChoiceBean {
    public ChoiceBean() {
    }

    //主键id
    @PrimaryKey(autoGenerate = true)
    public int mId;

    //转盘数据类型  0 内置数据  1 本地数据
    @ColumnInfo(name = "type")
    public int mType;


    //转盘标题
    @ColumnInfo(name = "title")
    public String mTitle = "";


    @TypeConverters(MyConverter.class)
    @ColumnInfo(name = "choice_list")
    public List<String> mChoiceList;

    //最后一次编辑时间
    @ColumnInfo(name = "end_time")
    public long mEndTime;

    public long getEndTime() {
        return mEndTime;
    }

    public void setEndTime(long mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }


    public List<String> getmChoiceList() {
        if (mChoiceList == null) {
            mChoiceList = new ArrayList<String>();
        }
        return mChoiceList;
    }

    public void setmChoiceList(List<String> mChoiceList) {
        this.mChoiceList = mChoiceList;
    }


    public boolean isValued(String[] errorMsg) {
        if (TextUtils.isEmpty(mTitle)) {
            errorMsg[0] = "标题为空或未保存";
            return false;
        }
        List<String> mChoiceList = getmChoiceList();
        if (mChoiceList == null || mChoiceList.size() == 0) {
            errorMsg[0] = "列表为空或未保存";
            return false;
        }

        for (int i = 0; i < mChoiceList.size(); i++) {
            if (TextUtils.isEmpty(mChoiceList.get(i))) {
                errorMsg[0] = "列表" + i + "为空或未保存";
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        return "ChoiceBean{" +
                "mId=" + mId +
                ", mType=" + mType +
                ", mTitle='" + mTitle + '\'' +
                ", mChoiceList=" + mChoiceList +
                '}';
    }
}
