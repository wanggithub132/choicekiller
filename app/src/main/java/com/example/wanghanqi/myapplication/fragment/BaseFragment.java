package com.example.wanghanqi.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.baidu.mobstat.StatService;
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

    @Override
    public void onResume() {
        super.onResume();
        // 页面埋点，需要使用Activity的引用，以便代码能够统计到具体页面名
        StatService.onResume(mActivity);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 页面结束埋点，需要使用Activity的引用，以便代码能够统计到具体页面名
        StatService.onPause(mActivity);
    }
}
