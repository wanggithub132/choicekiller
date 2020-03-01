package com.example.wanghanqi.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wanghanqi.myapplication.AddActivity;
import com.example.wanghanqi.myapplication.R;
import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.bean.Constance;
import com.example.wanghanqi.myapplication.utils.VLog;
import com.example.wanghanqi.myapplication.widget.LuckPan;
import com.example.wanghanqi.myapplication.widget.LuckPanAnimEndCallBack;
import com.google.gson.Gson;

/**
 * Created by wanghanqi on 2020/2/8.
 */

public class HomeFragment extends BaseFragment {
    private LuckPan pan;
    private ImageView imgStart;
    private Button mEditBtn;
    private Button mAddBtn;
    private ChoiceBean mChoiceBean = Constance.sFoodBean;

    public static  final int ADD_ACTIVITY_RESULT = 1001;
    private static  final int EDIT_RESULT = 1002;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choice,container,false);
        mEditBtn = root.findViewById(R.id.btn_edit);
        mAddBtn = root.findViewById(R.id.btn_add);
        pan = (LuckPan) root.findViewById(R.id.pan);
        imgStart = (ImageView) root.findViewById(R.id.img_start);


        //读取内置数据

        //读取历史数据

        //显示最后一次数据的转盘
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,AddActivity.class);
                Gson gson = new Gson();
                intent.putExtra(AddActivity.DATA_FLAG,gson.toJson(pan.getItems()));
                startActivityForResult(intent,EDIT_RESULT);

            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mActivity,AddActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_RESULT);

            }
        });

        pan.setItems(mChoiceBean);
        pan.setLuckNumber(3);
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
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_ACTIVITY_RESULT){
            VLog.d("","onActivityResult");
            try {
                String result = data.getStringExtra(AddActivity.RESULT_FLAG);
                Gson gson = new Gson();
                ChoiceBean bean = gson.fromJson(result, ChoiceBean.class);
                pan.setItems(bean);
            }catch (Exception e){}
        }
    }
}
