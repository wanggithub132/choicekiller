package com.example.wanghanqi.myapplication;

import android.support.v7.app.AppCompatActivity;

import com.baidu.mobstat.StatService;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        // 页面埋点，需要使用Activity的引用，以便代码能够统计到具体页面名
        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 页面结束埋点，需要使用Activity的引用，以便代码能够统计到具体页面名
        StatService.onPause(this);
    }
}
