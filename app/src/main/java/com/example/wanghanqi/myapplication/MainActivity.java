package com.example.wanghanqi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.wanghanqi.myapplication.fragment.AnswerFragment;
import com.example.wanghanqi.myapplication.fragment.BaseFragment;
import com.example.wanghanqi.myapplication.fragment.HomeFragment;
import com.example.wanghanqi.myapplication.fragment.SettingFragment;
import com.example.wanghanqi.myapplication.fragment.TwoChoiceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class MainActivity extends BaseActivity {

    List<BaseFragment> fragments = new ArrayList<>();

    private void buildFragmentList() {
        BaseFragment homeFragment = new HomeFragment();
        BaseFragment answerFragment = new AnswerFragment();
        BaseFragment twoChoiceFragment = new TwoChoiceFragment();
        BaseFragment settingFragment = new SettingFragment();
        fragments.add(homeFragment);
        fragments.add(twoChoiceFragment);
        fragments.add(answerFragment);
        fragments.add(settingFragment);
    }


    private void switchFragment(int pos, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, fragments.get(pos), tag)
                .commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildFragmentList();
        switchFragment(0,"");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0, "");
                    return true;
                case R.id.navigation_dashboard:
                    switchFragment(1, "");
                    return true;
                case R.id.navigation_two:
                    switchFragment(2,"");
                    return true;
                case R.id.navigation_notifications:
                    switchFragment(3, "");
                    return true;
            }
            return false;
        }
    };


}
