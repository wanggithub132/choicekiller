package com.example.wanghanqi.myapplication.bean;

import java.util.Arrays;

/**
 * Created by wanghanqi on 2020/2/6.
 */

public class Constance {
    public static String[] mItemStrs = {"选项一","选项二","选项三","选项四","选项五","选项六"};
    public static String[] sExampleList = {"正","反","正","反","正","反","正","反"};
    public static ChoiceBean sFoodBean = new ChoiceBean();
    public static ChoiceBean sExample = new ChoiceBean();
    static{
        sFoodBean.setTitle("示例");
        sFoodBean.setmChoiceList(Arrays.asList(mItemStrs));

        sExample.setTitle("示例");
        sExample.setmChoiceList(Arrays.asList(sExampleList));
    }

}
