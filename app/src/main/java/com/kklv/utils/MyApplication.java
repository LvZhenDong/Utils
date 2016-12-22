package com.kklv.utils;

import android.app.Application;

import com.kklv.utils.utils.L;
import com.orhanobut.logger.Logger;

import org.xutils.x;

public class MyApplication extends Application {
    private final static String LOG_TAG = "LinLin";
    public final static boolean IS_DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        x.Ext.init(this);
        Logger.init(LOG_TAG).methodOffset(1);
    }
}
