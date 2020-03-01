package com.example.wanghanqi.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wanghanqi.myapplication.R;
import com.example.wanghanqi.myapplication.utils.BaseLib;
import com.example.wanghanqi.myapplication.widget.CommonDialog;
import com.example.wanghanqi.myapplication.widget.SettingView;

/**
 * Created by wanghanqi on 2020/2/8.
 */

public class SettingFragment extends BaseFragment {
    SettingView mContestUsLayout;
    SettingView mPrivacyLayout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        mContestUsLayout = root.findViewById(R.id.contact_us);
        mContestUsLayout.setText("联系我们");
        mContestUsLayout.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialog.isShowing()){
                    mDialog.dismiss();
                }
                View contentView = inflater.inflate(R.layout.content_view_us, container, false);
                mDialog.setTitleLabel("截屏保存后识别二维码加入群组").setContentViewLayout(contentView).buildDialog();
                mDialog.show();
            }
        });

        mPrivacyLayout = root.findViewById(R.id.yinsi);
        mPrivacyLayout.setText("使用须知");
        mPrivacyLayout.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonDialog dialog = new CommonDialog(mActivity);
                dialog.setTitleLabel("使用须知").setMessageLabel("  本软件为开发者学习项目，欢迎大家交流反馈，如果app中的内容涉及到版权等问题，敬请指出，我们将配合您进行改正").buildDialog();
                dialog.show();

            }
        });
        return root;
    }
}
