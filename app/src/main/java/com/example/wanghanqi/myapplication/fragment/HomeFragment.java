package com.example.wanghanqi.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanghanqi.myapplication.AddActivity;
import com.example.wanghanqi.myapplication.R;
import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.bean.Constance;
import com.example.wanghanqi.myapplication.db.DbManager;
import com.example.wanghanqi.myapplication.db.MyDao;
import com.example.wanghanqi.myapplication.db.MyDb;
import com.example.wanghanqi.myapplication.mvp.HomeConstant;
import com.example.wanghanqi.myapplication.mvp.HomePresenter;
import com.example.wanghanqi.myapplication.utils.DpPxUtils;
import com.example.wanghanqi.myapplication.utils.ThreadUtils;
import com.example.wanghanqi.myapplication.utils.VLog;
import com.example.wanghanqi.myapplication.widget.LuckPan;
import com.example.wanghanqi.myapplication.widget.LuckPanAnimEndCallBack;
import com.example.wanghanqi.myapplication.widget.TagLayout;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by wanghanqi on 2020/2/8.
 */

public class HomeFragment extends BaseFragment implements HomeConstant.HomeView {
    private LuckPan pan;
    private TextView pan_title;
    private ImageView imgStart;
    private Button mEditBtn;
    private Button mAddBtn;
    private HomeConstant.Presenter mHomePresenter;
    public static final int ADD_ACTIVITY_RESULT = 1001;
    private static final int EDIT_RESULT = 1002;

    private TagLayout mTagLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choice, container, false);
        initView(root);

        mHomePresenter = new HomePresenter(this);
        //读取内置数据

        //加载历史数据
        mHomePresenter.loadHistory();
        //加载最后一次数据
        mHomePresenter.loadLatest();
        return root;
    }

    private void initView(View root) {
        mEditBtn = root.findViewById(R.id.btn_edit);
        mAddBtn = root.findViewById(R.id.btn_add);
        pan = (LuckPan) root.findViewById(R.id.pan);
        pan_title = root.findViewById(R.id.pan_title);
        imgStart = (ImageView) root.findViewById(R.id.img_start);
        //显示最后一次数据的转盘
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, AddActivity.class);
                Gson gson = new Gson();
                intent.putExtra(AddActivity.DATA_FLAG, gson.toJson(pan.getItems()));
                startActivityForResult(intent, EDIT_RESULT);

            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mActivity, AddActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_RESULT);

            }
        });

        pan.setLuckPanAnimEndCallBack(new LuckPanAnimEndCallBack() {
            @Override
            public void onAnimEnd(String str) {
                Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
            }
        });

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pan.startAnim();
            }
        });

        mTagLayout = root.findViewById(R.id.tag_layout);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VLog.d("", "onActivityResult" + requestCode);
        if (requestCode == ADD_ACTIVITY_RESULT || requestCode == EDIT_RESULT) {
            VLog.d("", "onActivityResult");
            try {
                final String idRresult = data.getStringExtra(AddActivity.RESULT_FLAG);
                VLog.d("", "idRresult" + idRresult);
                ThreadUtils.getsInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        final ChoiceBean bean = DbManager.getsInstance().findBeanById(idRresult);

                        if(bean == null){
                            return;
                        }
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refreshPan(bean);
                            }
                        });
                    }
                });


            } catch (Exception e) {
            }
        }

    }

    @Override
    public void updateHistoryList(List<ChoiceBean> historyList) {

        for(final ChoiceBean bean:historyList) {

            Button view = new Button(mActivity);

            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(DpPxUtils.dip2px(mActivity,100), ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            view.setText(bean.getTitle());
            view.setMaxLines(1);
            view.setMinWidth(DpPxUtils.dip2px(mActivity,0));
            view.setMinHeight(DpPxUtils.dip2px(mActivity,0));
            view.setBackground(getResources().getDrawable(R.drawable.common_button_selector));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    refreshPan(bean);
                }
            });
            mTagLayout.addView(view);
        }
        mTagLayout.invalidate();

    }

    @Override
    public void updateLatestList(ChoiceBean latestBean) {
        refreshPan(latestBean);
    }

    private void refreshPan(ChoiceBean bean) {
        if (bean != null && bean.isValued()) {
            pan.setItems(bean);
            pan_title.setText("标题："+bean.getTitle());


        }
    }
}
