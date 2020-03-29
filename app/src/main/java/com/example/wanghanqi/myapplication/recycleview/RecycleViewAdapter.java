package com.example.wanghanqi.myapplication.recycleview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wanghanqi.myapplication.R;
import com.example.wanghanqi.myapplication.bean.ChoiceBean;
import com.example.wanghanqi.myapplication.utils.VLog;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter {
    private static final int TITLE_TYPE = 1;
    private static final int CONTENT_TYPE = 2;

    private int mType;

    private ChoiceBean mData = new ChoiceBean();

    private ChoiceBean mTempDate = new ChoiceBean();

    public RecycleViewAdapter() {

    }

    private View.OnClickListener mTitleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            VLog.d("", "新增条目");
            mData.getmChoiceList().add("");
            VLog.d( mData.getmChoiceList().toString());
            notifyDataSetChanged();
        }
    };

    public void setData(ChoiceBean bean,int type) {
        if (bean == null) {
            bean = new ChoiceBean();
        }
        mData = bean;
        mTempDate = bean;
        mType = type;
        notifyDataSetChanged();
    }

    public ChoiceBean getData(){
        return mData;
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
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TITLE_TYPE) {
            ((TitleHolder) holder).onBind(mData);
        } else if (getItemViewType(position) == CONTENT_TYPE) {
            ((ContentHolder) holder).onBind(mData, position);
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
        return 1  + listSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITLE_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    /**
     * 头部标题view
     */
    static class TitleHolder extends RecyclerView.ViewHolder {
        private EditText mTitle;
        private ImageView mAddBtn;

        public TitleHolder(View itemView, View.OnClickListener listener, final OnEditListenrt editListenrt) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.holder_title);
            mAddBtn = itemView.findViewById(R.id.holder_title_btn_add);
            mAddBtn.setOnClickListener(listener);
            mTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    editListenrt.onClick(0, mTitle.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        public void onBind(ChoiceBean bean) {
            if (bean == null) {
                return;
            }
            mTitle.setText(bean.getTitle());
        }
    }

    /**
     * 内容holder
     */
    static class ContentHolder extends RecyclerView.ViewHolder {
        private EditText mContent;
        private ImageView mDelBtn;

        private OnDelClickListener mDelListener;
        private OnEditListenrt mEditListener;

        public ContentHolder(View itemView, OnDelClickListener listener, OnEditListenrt listener2) {
            super(itemView);
            setIsRecyclable(false);
            mDelListener = listener;
            mEditListener = listener2;
            mContent = itemView.findViewById(R.id.holder_content);
            mDelBtn = itemView.findViewById(R.id.holder_btn_del);


        }

        public void onBind(ChoiceBean bean, final int pos) {
            mContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mEditListener.onClick(pos, s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if (bean != null && bean.getmChoiceList() != null) {
                if (pos - 1 <= bean.getmChoiceList().size()) {
                    mContent.setText(bean.getmChoiceList().get(pos - 1));
                }
            }

            mDelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDelListener.onClick(pos, v);
                }
            });




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
