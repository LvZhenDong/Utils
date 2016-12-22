package com.kklv.utils;

import android.app.Application;

import com.kklv.utils.utils.L;
import com.orhanobut.logger.Logger;

import org.xutils.x;

public class MyApplication extends Application {
    private final static String LOG_TAG = "LinLin";
    public final static boolean IS_DEBUG = true;
    private static MyApplication sMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        sMyApplication=this;
        init();
    }

    private void init() {
        x.Ext.init(this);
        Logger.init(LOG_TAG).methodOffset(1);
    }

    public static MyApplication getInstance(){
        return sMyApplication;
    }
}
