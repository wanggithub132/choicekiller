package com.example.wanghanqi.myapplication.mvp;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;

import java.util.List;

public class HomeConstant {


    public interface Presenter {
        void loadHistory();
        void loadLatest();

    }

    public interface HomeView {
        void updateHistoryList(List<ChoiceBean> historyList );
        void updateLatestList(ChoiceBean latestBean);

    }
}
