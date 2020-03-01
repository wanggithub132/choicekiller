package com.example.wanghanqi.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.wanghanqi.myapplication.widget.CommonDialog;

/**
 * Created by wanghanqi on 2020/2/8.
 */

public class BaseFragment extends Fragment {
    protected Activity mActivity;

    protected CommonDialog mDialog ;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mDialog = new CommonDialog(mActivity);
    }
}
