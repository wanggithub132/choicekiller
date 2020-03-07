package com.example.wanghanqi.myapplication.utils;

import android.os.Handler;
import android.os.Looper;

public class MainThreadUtils {
    private final static Handler MAIN = new Handler(Looper.getMainLooper());

    public static void postMainThread(final Runnable runnbale) {
        MAIN.post(new Runnable() {

            public void run() {
                try {
                    runnbale.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

}
