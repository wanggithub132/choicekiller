package com.example.wanghanqi.myapplication.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wanghanqi.myapplication.R;
import com.example.wanghanqi.myapplication.bean.WordsMap;

import java.util.Random;

/**
 * Created by wanghanqi on 2020/2/8.
 */

public class AnswerFragment extends BaseFragment {

    FrameLayout mFlContainer;
    FrameLayout mFlCardBack;
    FrameLayout mFlCardFront;

    private TextView mAnswerText;

    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private boolean mIsShowBack;
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_answer, container, false);
        initView(root);
        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离
        return root;
    }

    private void initView(View root) {
        mFlContainer = root.findViewById(R.id.main_fl_container);
        mFlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
        mFlCardBack = root.findViewById(R.id.main_fl_card_back);
        mFlCardFront = root.findViewById(R.id.main_fl_card_front);
        mAnswerText = root.findViewById(R.id.answer_text);
        random = new Random();
    }


    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(mActivity, R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(mActivity, R.animator.anim_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mFlContainer.setClickable(false);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFlContainer.setClickable(true);
            }
        });
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
    }

    // 翻转卡片
    public void flipCard() {
        // 正面朝上
        if (!mIsShowBack) {
            mRightOutSet.setTarget(mFlCardFront);
            mLeftInSet.setTarget(mFlCardBack);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = true;
            int randomTemp = random.nextInt( WordsMap.getAnswerList().size());
            mAnswerText.setText(WordsMap.getAnswerList().get(randomTemp));

        } else { // 背面朝上
            mRightOutSet.setTarget(mFlCardBack);
            mLeftInSet.setTarget(mFlCardFront);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = false;

        }
    }

}
