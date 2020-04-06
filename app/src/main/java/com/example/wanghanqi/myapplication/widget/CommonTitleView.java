package com.example.wanghanqi.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wanghanqi.myapplication.R;

/**
 * Created by wanghanqi on 2020/2/9.
 */

public class CommonTitleView extends RelativeLayout {
    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivMore;

    public CommonTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context,attrs);
    }
      private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.common_title_layout, this);
        ivBack = inflate.findViewById(R.id.iv_back);
        tvTitle = inflate.findViewById(R.id.tv_title);
        ivMore = inflate.findViewById(R.id.iv_more);

        init(context,attributeSet);
    }

    //初始化资源文件
    public void init(Context context, AttributeSet attributeSet){
    }

    //左边图片点击事件
    public void setLeftIconOnClickListener(OnClickListener l){
        ivBack.setVisibility(VISIBLE);
        ivBack.setOnClickListener(l);
    }

    //右边图片点击事件
    public void setRightIconOnClickListener(OnClickListener l){
        ivMore.setVisibility(VISIBLE);
        ivMore.setOnClickListener(l);
    }

    public void setRightImage(int drawableResId){
        ivMore.setImageResource(drawableResId);
    }


}
