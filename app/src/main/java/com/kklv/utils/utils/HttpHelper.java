package com.kklv.utils.utils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/12/21.
 */

public class HttpHelper {

    private HttpHelper() {

    }

    public static void getHttpRequest(String url, HttpRequestParams httpRequestParams,
                                      HttpCallBack callBack) {
        RequestParams params = new RequestParams(url);
        for (HttpRequestParams.KeyValue item : httpRequestParams.mParamsList) {
            params.addBodyParameter(item.getKey(),item.getValue());
        }

        sendHttpRequest(HttpMethod.GET, params, callBack);
    }

    public static void postHttpRequest(String url,HttpRequestParams httpRequestParams,HttpCallBack callBack){
        RequestParams params = new RequestParams(url);
        for (HttpRequestParams.KeyValue item : httpRequestParams.mParamsList) {
            params.addBodyParameter(item.getKey(),item.getValue());
        }

        sendHttpRequest(HttpMethod.POST, params, callBack);
    }

    public static Callback.Cancelable sendHttpRequest(HttpMethod method, RequestParams params,
                                                      final HttpCallBack callBack) {
        if (params == null) {
            params = new RequestParams();
        }

        return x.http().request(method, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result == null) {
                    return;
                } else {
                    callBack.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onError(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                callBack.onFinished();
            }
        });
    }
}
