package com.example.wanghanqi.myapplication.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wanghanqi.myapplication.R;

/**
 * Created by wanghanqi on 2020/2/16.
 */

public class SettingView extends LinearLayout {
    private TextView mText;
    private ImageView mBtn;
    public SettingView(Context context) {
        super(context);
    }

    public SettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.setting_list_item, this);
        mText = inflate.findViewById(R.id.text_item);
        mBtn = inflate.findViewById(R.id.btn_item);
    }


    public void setText(String text){
        mText.setText(text);
    }

    public void setClickListener(OnClickListener listener){
        mBtn.setOnClickListener(listener);
    }
}
