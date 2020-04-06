package com.example.wanghanqi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.db.DbManager;
import com.example.wanghanqi.myapplication.fragment.HomeFragment;
import com.example.wanghanqi.myapplication.recycleview.RecycleViewAdapter;
import com.example.wanghanqi.myapplication.utils.ThreadUtils;
import com.example.wanghanqi.myapplication.utils.VLog;
import com.example.wanghanqi.myapplication.widget.CommonTitleView;
import com.google.gson.Gson;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class AddActivity extends BaseActivity {
    public static final String DATA_FLAG = "choice_bean_flag";
    public static final String RESULT_FLAG = "result_flag";


    private CommonTitleView mCommonTitleView;
    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mAdapter;

    private int mType; //0新增数据 1 编辑数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addandedit);

        initView();

        Intent intent = getIntent();
        String choiceJson = intent.getStringExtra(DATA_FLAG);
        Gson gson = new Gson();

        ChoiceBean choiceBean = gson.fromJson(choiceJson, ChoiceBean.class);
        if (choiceBean == null) {
            //新增功能
            choiceBean = new ChoiceBean();
            choiceBean.setmType(1);
            mType =0;

        }else{
            //编辑功能
            mType = 1;
        }

        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecycleViewAdapter();

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(choiceBean,mType);



    }

    private void initView() {
        mCommonTitleView = findViewById(R.id.title);
        mCommonTitleView.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCommonTitleView.setRightImage(R.drawable.chose_ok);
        mCommonTitleView.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChoiceBean mData = mAdapter.getData();
                //确认按钮监听
                //数据校验
                String[] result = new String[1];
                if (mData == null || !mData.isValued(result)) {
                    Toast.makeText(AddActivity.this, result[0], Toast.LENGTH_SHORT).show();
                    return;
                }
                VLog.d("回传数据", mData.toString());
                //数据保存
                ThreadUtils.getsInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        //来自新增的数据
                        if(mType ==0) {
                            DbManager.getsInstance().inster(mData);
                        }
                        //来自编辑的数据
                        if(mType ==1) {
                            DbManager.getsInstance().updateItem(mData);
                        }
                    }
                });

                Intent i = new Intent();
                i.putExtra(AddActivity.RESULT_FLAG, mData.getTitle());
                setResult(HomeFragment.ADD_ACTIVITY_RESULT, i);
                finish();
            }
        });
    }
}
