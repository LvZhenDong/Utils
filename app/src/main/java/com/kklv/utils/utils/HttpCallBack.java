package com.kklv.utils.utils;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface HttpCallBack {

    void onSuccess(String result);

    void onError(String msg);

    void onFinished();
}
