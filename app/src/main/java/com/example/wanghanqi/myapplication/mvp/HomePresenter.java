package com.example.wanghanqi.myapplication.mvp;

import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.bean.Constance;
import com.example.wanghanqi.myapplication.db.DbManager;
import com.example.wanghanqi.myapplication.utils.MainThreadUtils;
import com.example.wanghanqi.myapplication.utils.ThreadUtils;
import com.example.wanghanqi.myapplication.utils.VLog;

import java.util.List;

public class HomePresenter implements HomeConstant.Presenter {
    private HomeConstant.HomeView mView;

    public HomePresenter(HomeConstant.HomeView view) {
        mView = view;

    }

    @Override
    public void loadHistory() {

        //读取历史数据
        ThreadUtils.getsInstance().execute(new Runnable() {
            @Override
            public void run() {
                final List<ChoiceBean> historyList = DbManager.getsInstance().findHistoryBean();
                if (historyList != null && historyList.size() > 0) {
                    for (ChoiceBean b : historyList)
                        VLog.d(b.toString());

                    MainThreadUtils.postMainThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mView != null) {
                                mView.updateHistoryList(historyList);
                            }
                        }
                    });

                }


            }
        });
    }

    @Override
    public void loadLatest() {
        //读取历史数据
        ThreadUtils.getsInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ChoiceBean latest = DbManager.getsInstance().findLatestBean();
                    if (latest != null) {
                        VLog.d("latest" + latest.toString());
                    } else {
                        latest = Constance.sFoodBean;
                        DbManager.getsInstance().inster(latest);
                    }
                    final ChoiceBean finalLatest = latest;
                    MainThreadUtils.postMainThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mView != null) {
                                mView.updateLatestList(finalLatest);
                            }
                        }
                    });
                } catch (Exception e) {

                }


            }
        });

    }
}
