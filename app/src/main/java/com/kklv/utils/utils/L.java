package com.kklv.utils.utils;

import com.kklv.utils.MyApplication;
import com.orhanobut.logger.Logger;

/**
 * 日志统一打印类，与Log类的使用方法一样，封装自Logger
 *
 * @author LvZhenDong
 *         created at 2016/12/12 10:28
 */
public class L {

    //防止生成对象
    private L() {
        throw new UnsupportedOperationException("不要生成这个类的对象");
    }

    //这4个是默认的TAG
    public static void i(String msg) {
        if (MyApplication.IS_DEBUG) Logger.i(msg);
    }

    public static void d(String msg) {
        if (MyApplication.IS_DEBUG) Logger.d(msg);
    }

    public static void e(String msg) {
        if (MyApplication.IS_DEBUG) Logger.e(msg);
    }

    public static void v(String msg) {
        if (MyApplication.IS_DEBUG) Logger.v(msg);
    }

    //自定义TAG
    public static void i(String tag, String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(tag).i(msg);
    }

    public static void d(String tag, String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(tag).d(msg);
    }

    public static void e(String tag, String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(tag).e(msg);
    }

    public static void v(String tag, String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(tag).v(msg);
    }

    //使用类名作为TAG
    public static void iWithClassName(String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(getClassName()).i(msg);
    }

    public static void dWithClassName(String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(getClassName()).d(msg);
    }

    public static void eWithClassName(String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(getClassName()).e(msg);
    }

    public static void vWithClassName(String msg) {
        if (MyApplication.IS_DEBUG) Logger.t(getClassName()).v(msg);
    }

    /**
     * 获取类名
     *
     * @return
     */
    private static String getClassName() {
        String result;
        // 这里的数组的index2是根据你工具类的层级做不同的定义，这里仅仅是关键代码
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());

        return result;
    }
}
