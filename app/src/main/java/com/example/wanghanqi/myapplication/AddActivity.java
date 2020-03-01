package com.example.wanghanqi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.recycleview.RecycleViewAdapter;
import com.google.gson.Gson;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class AddActivity extends BaseActivity {
    public static final String DATA_FLAG = "choice_bean_flag";
    public static final String RESULT_FLAG = "result_flag";

    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addandedit);

        Intent intent = getIntent();
        String choiceJson = intent.getStringExtra(DATA_FLAG);
        Gson gson = new Gson();

        ChoiceBean choiceBean = gson.fromJson(choiceJson, ChoiceBean.class);
        if (choiceBean == null) {
            //新增功能
            choiceBean = new ChoiceBean();
            choiceBean.setmType(1);

        }else{
            //编辑功能
        }

        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecycleViewAdapter(this);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(choiceBean);



    }
}
