package com.example.wanghanqi.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanghanqi.myapplication.R;
import com.example.wanghanqi.myapplication.bean.Constance;
import com.example.wanghanqi.myapplication.bean.WordsMap;
import com.example.wanghanqi.myapplication.utils.VLog;
import com.example.wanghanqi.myapplication.widget.LuckPan;
import com.example.wanghanqi.myapplication.widget.LuckPanAnimEndCallBack;

import java.util.Random;

public class TwoChoiceFragment extends BaseFragment {

    private LuckPan pan;
    private ImageView imgStart;
    private TextView mAnswer;
    private Random random;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_two_choice, container, false);
        VLog.d("create");
        initView(root);
        return root;
    }

    private void initView(View root) {
        pan = (LuckPan) root.findViewById(R.id.pan);
        imgStart = (ImageView) root.findViewById(R.id.img_start);
        random = new Random();
        pan.setLuckPanAnimEndCallBack(new LuckPanAnimEndCallBack() {
            @Override
            public void onAnimEnd(String str) {
                Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
                int randomTemp = random.nextInt( WordsMap.getAnswerList().size());
                mAnswer.setText(WordsMap.getAnswerList().get(randomTemp));
            }
        });

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pan.startAnim();
            }
        });
        pan.setItems(Constance.sExample);
        pan.setmRepeatCount(30);
        pan.setAnimalTime(6000);
        mAnswer = root.findViewById(R.id.answer);

    }
}
