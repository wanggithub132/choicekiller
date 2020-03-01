package com.example.wanghanqi.myapplication.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wanghanqi.myapplication.AddActivity;
import com.example.wanghanqi.myapplication.R;
import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.db.MyDb;
import com.example.wanghanqi.myapplication.fragment.HomeFragment;
import com.example.wanghanqi.myapplication.utils.ThreadUtils;
import com.example.wanghanqi.myapplication.utils.VLog;

import static android.app.Activity.RESULT_CANCELED;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter {
    private static final int TITLE_TYPE = 1;
    private static final int CONTENT_TYPE = 2;
    private static final int FOOTER_TYPE = 3;
    private Activity mActivity;

    private ChoiceBean mData = new ChoiceBean();

    public RecycleViewAdapter(Activity activity) {
        mActivity = activity;
    }

    private View.OnClickListener mTitleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            VLog.d("", "新增条目");
            mData.getmChoiceList().add("");

            notifyDataSetChanged();
        }
    };

    public void setData(ChoiceBean bean) {
        if (bean == null) {
            bean = new ChoiceBean();
        }
        mData = bean;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TITLE_TYPE) {
            View view = View.inflate(parent.getContext(), R.layout.recycle_holder_title, null);
            return new TitleHolder(view, mTitleListener, new OnEditListenrt() {
                @Override
                public void onClick(int pos, String msg) {
                    mData.setTitle(msg);
                }
            });
        } else if (viewType == CONTENT_TYPE) {
            View view = View.inflate(parent.getContext(), R.layout.recycle_holder_content, null);
            return new ContentHolder(view, new OnDelClickListener() {
                @Override
                public void onClick(int pos, View view) {
                    if (mData != null && mData.getmChoiceList() != null) {
                        if (pos - 1 <= mData.getmChoiceList().size()) {
                            mData.getmChoiceList().remove(pos - 1);
                        }
                    }
                    notifyDataSetChanged();
                }
            }, new OnEditListenrt() {
                @Override
                public void onClick(int pos, String msg) {
                    if (mData != null && mData.getmChoiceList() != null) {
                        if (pos - 1 <= mData.getmChoiceList().size()) {
                            mData.getmChoiceList().set(pos - 1, msg);
                        }
                    }
                }
            });
        } else if (viewType == FOOTER_TYPE) {
            View view = View.inflate(parent.getContext(), R.layout.recycle_holder_footer, null);
            return new FooterHolder(view, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //确认按钮监听
                    //数据校验
                    String[] result = new String[1];
                    if (mData == null || !mData.isValued(result)) {
                        Toast.makeText(mActivity, result[0], Toast.LENGTH_SHORT).show();
                        return;
                    }
                    VLog.d("回传数据", mData.toString());
                    //数据保存
                    ThreadUtils.getsInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            MyDb.getsInstance().getMyDao().inster(mData);
                        }
                    });

                    Intent i = new Intent();

                    i.putExtra(AddActivity.RESULT_FLAG, mData.getTitle());
                    mActivity.setResult(HomeFragment.ADD_ACTIVITY_RESULT, i);
                    mActivity.finish();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //取消按钮监听
                    mActivity.setResult(RESULT_CANCELED);
                    mActivity.finish();

                }
            });
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TITLE_TYPE) {
            ((TitleHolder) holder).onBind(mData);
        } else if (getItemViewType(position) == CONTENT_TYPE) {
            ((ContentHolder) holder).onBind(mData, position);
        } else if (getItemViewType(position) == FOOTER_TYPE) {
            ((FooterHolder) holder).onBind(mData);
        }


    }

    /**
     * 头部+尾部+列表长度
     *
     * @return
     */
    @Override
    public int getItemCount() {
        int listSize = 0;
        if (mData != null && mData.getmChoiceList() != null) {
            listSize = mData.getmChoiceList().size();
        }
        return 1 + 1 + listSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITLE_TYPE;
        } else if (position == getItemCount() - 1) {
            return FOOTER_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    /**
     * 头部标题view
     */
    static class TitleHolder extends RecyclerView.ViewHolder {
        private EditText mTitle;
        private Button mAddBtn;
        private Button mEditBtn;

        public TitleHolder(View itemView, View.OnClickListener listener, final OnEditListenrt editListenrt) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.holder_title);
            mTitle.setEnabled(false);//设置不可编辑
            mAddBtn = itemView.findViewById(R.id.holder_title_btn_add);
            mAddBtn.setOnClickListener(listener);
            mEditBtn = itemView.findViewById(R.id.holder_title_btn_edit);
            mEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTitle.setEnabled(!mTitle.isEnabled());
                    if (mTitle.isEnabled()) {
                        mEditBtn.setText("保存");

                    } else {
                        mEditBtn.setText("编辑");
                        editListenrt.onClick(0, mTitle.getText().toString());
                    }
                }
            });
        }

        public void onBind(ChoiceBean bean) {
            if (bean == null) {
                return;
            }
            mTitle.setText(bean.getTitle());
            if (mTitle.isEnabled()) {
                mEditBtn.setText("保存");
            } else {
                mEditBtn.setText("编辑");
            }


        }
    }

    /**
     * 内容holder
     */
    static class ContentHolder extends RecyclerView.ViewHolder {
        private EditText mContent;
        private Button mEditBtn;
        private Button mDelBtn;

        private OnDelClickListener mDelListener;
        private OnEditListenrt mEditListener;

        public ContentHolder(View itemView, OnDelClickListener listener, OnEditListenrt listener2) {
            super(itemView);
            mDelListener = listener;
            mEditListener = listener2;
            mContent = itemView.findViewById(R.id.holder_content);
            mContent.setEnabled(false);//设置不可编辑
            mEditBtn = itemView.findViewById(R.id.holder_btn_edit);
            mDelBtn = itemView.findViewById(R.id.holder_btn_del);


        }

        public void onBind(ChoiceBean bean, final int pos) {
            if (bean != null && bean.getmChoiceList() != null) {
                if (pos - 1 <= bean.getmChoiceList().size()) {
                    mContent.setText(bean.getmChoiceList().get(pos - 1));
                }
            }
            if (mContent.isEnabled()) {
                mEditBtn.setText("保存");
            } else {
                mEditBtn.setText("编辑");
            }
            mDelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDelListener.onClick(pos, v);
                }
            });
            mEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContent.setEnabled(!mContent.isEnabled());
                    if (mContent.isEnabled()) {
                        mEditBtn.setText("保存");

                    } else {
                        mEditBtn.setText("编辑");
                        mEditListener.onClick(pos, mContent.getText().toString());
                    }
                }
            });


        }
    }

    /**
     * 弹窗底部选择按钮holder
     */
    static class FooterHolder extends RecyclerView.ViewHolder {
        private Button mOkBtn;
        private Button mCancelBtn;

        public FooterHolder(View itemView, View.OnClickListener okListener, View.OnClickListener canListener) {
            super(itemView);
            mOkBtn = itemView.findViewById(R.id.holder_footer_btn_ok);
            mOkBtn.setOnClickListener(okListener);
            mCancelBtn = itemView.findViewById(R.id.holder_footer_btn_cancel);
            mCancelBtn.setOnClickListener(canListener);
        }

        public void onBind(ChoiceBean bean) {


        }
    }


    /**
     * 删除接口
     */
    interface OnDelClickListener {
        void onClick(int pos, View view);
    }

    interface OnEditListenrt {
        void onClick(int pos, String msg);
    }

}
