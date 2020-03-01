package com.example.wanghanqi.myapplication.bean;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class Constance {
    public static String[] mItemStrs = {"沙县小吃","黄焖鸡","晋家门","食其家","老母鸡汤面","汉堡王","哥哥哥","对效果"};
    public static String[] sExampleList = {"选项一","选项二","选项三","选项四","选项五","选项六","选项七"};
    public static ChoiceBean sFoodBean = new ChoiceBean();
    public static ChoiceBean sExample = new ChoiceBean();
    static{
        sFoodBean.setTitle("吃");
        sFoodBean.setmChoiceList(Arrays.asList(mItemStrs));

        sExample.setTitle("示例");
        sExample.setmChoiceList(Arrays.asList(sExampleList));
    }

}
