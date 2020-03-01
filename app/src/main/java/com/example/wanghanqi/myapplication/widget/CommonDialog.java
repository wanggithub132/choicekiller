package com.example.wanghanqi.myapplication.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wanghanqi.myapplication.R;


/**
 * Created by wanghanqi on 2020/2/16.
 * 公共弹窗
 */

public class CommonDialog extends Dialog {
    private TextView mTitleView;
    private TextView mMessageView;
    private FrameLayout mContentLayout;
    private RelativeLayout mPositiveLayout;
    private TextView mPositiveView;
    private RelativeLayout mNegtiveLayout;
    private TextView mNegtiveView;

    private Resources mRes;
    private String mTitleLabel;
    private String mMessageLabel;
    private String mPositiveLabel;
    private String mNegtiveLabel;
    private View mContentView;
    private Context mContext;

    private View.OnClickListener mPositiveClickListener;
    private View.OnClickListener mNegtiveClickListener;

    public CommonDialog(@NonNull Context context) {

        this(context, R.style.style_common_dialog);

    }

    public CommonDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected CommonDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    private void init(Context context){
        mContext = context;
        mRes = context.getResources();
        setContentView(R.layout.dialog_common);
        setCanceledOnTouchOutside(true);
        mTitleView = findViewById(R.id.common_dialog_title);
        mContentLayout = findViewById(R.id.common_dialog_content_view);
        mMessageView = findViewById(R.id.common_dialog_message);
        mPositiveView = findViewById(R.id.common_dialog_positive_btn);
        mPositiveLayout = findViewById(R.id.common_dialog_positive_layout);
        mNegtiveLayout = findViewById(R.id.common_dialog_negitive_layout);
        mNegtiveView = findViewById(R.id.common_dialog_negitive_btn);

    }

    public CommonDialog setTitleLabel(String title){
        mTitleLabel = title;
        return  this;
    }


    public CommonDialog setTitleLabel(int titleId){
        mTitleLabel = mRes.getString(titleId);
        return  this;
    }

    public CommonDialog setMessageLabel(String message){
        mMessageLabel = message;
        return this;
    }

    public CommonDialog setContentViewLayout(View view){
        mContentView = view;
        return this;
    }


    public CommonDialog setPostiveButton(String postiveLabel,View.OnClickListener listener){
        mPositiveLabel = postiveLabel;
        mPositiveClickListener = listener;
        return this;
    }

    public CommonDialog setNegtiveButton(String negtiveLabel,View.OnClickListener listener){
        mNegtiveLabel = negtiveLabel;
        mNegtiveClickListener = listener;
        return this;
    }


    public  void buildDialog(){
        if(!TextUtils.isEmpty(mTitleLabel)){
            mTitleView.setText(mTitleLabel);
            mTitleView.setVisibility(View.VISIBLE);
        }else{
            mTitleView.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(mMessageLabel)){
            mMessageView.setText(mMessageLabel);
            mMessageView.setVisibility(View.VISIBLE);
            if(TextUtils.isEmpty(mTitleLabel)){
                LinearLayout.LayoutParams msgLp = (LinearLayout.LayoutParams) mMessageView.getLayoutParams();
                msgLp.topMargin = 60;
            }
        }else{
            mMessageView.setVisibility(View.GONE);
        }

        if(mContentView != null){
            mContentLayout.addView(mContentView);
            mContentLayout.setVisibility(View.VISIBLE);
        }else{
            mContentLayout.setVisibility(View.GONE);
        }

        if(mPositiveClickListener != null){
            mPositiveLayout.setOnClickListener(mPositiveClickListener);
        }else{
            mPositiveLayout.setOnClickListener(new DefaultClickListener());
        }

        if(mNegtiveClickListener != null){
            mNegtiveLayout.setOnClickListener(mNegtiveClickListener);
        }else{
            mNegtiveLayout.setOnClickListener(new DefaultClickListener());
        }

        if(!TextUtils.isEmpty(mPositiveLabel)){
            mPositiveView.setText(mPositiveLabel);
            mPositiveLayout.setVisibility(View.VISIBLE);
        }else{
            mPositiveLayout.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(mNegtiveLabel)){
            mNegtiveView.setText(mNegtiveLabel);
            mNegtiveLayout.setVisibility(View.VISIBLE);
        }else{
            mNegtiveLayout.setVisibility(View.GONE);
        }
    }

    private class DefaultClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    @Override
    public void show() {
        if(mContext instanceof Activity && ((Activity ) mContext).isFinishing()){
            return;
        }
        super.show();
    }
}
