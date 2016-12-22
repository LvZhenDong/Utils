package com.kklv.utils.utils;

import android.content.Context;

import com.kklv.utils.MyApplication;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

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
            params.addBodyParameter(item.getKey(), item.getValue());
        }
        setSSL(params);
        sendHttpRequest(HttpMethod.GET, params, callBack);
    }

    public static void postHttpRequest(String url, HttpRequestParams httpRequestParams,
                                       HttpCallBack callBack) {
        RequestParams params = new RequestParams(url);
        for (HttpRequestParams.KeyValue item : httpRequestParams.mParamsList) {
            params.addBodyParameter(item.getKey(), item.getValue());
        }
        setSSL(params);
        sendHttpRequest(HttpMethod.POST, params, callBack);
    }

    private static void setSSL(RequestParams params) {
        SSLContext sslContext = getSSLContext(MyApplication.getInstance());
        if (sslContext != null) {
            params.setSslSocketFactory(sslContext.getSocketFactory()); //绑定SSL证书(https请求)
        }
    }

    private static SSLContext getSSLContext(Context context) {
        CertificateFactory certificateFactory;
        InputStream inputStream;
        Certificate cer;
        KeyStore keystore;
        SSLContext sslContext;
        TrustManagerFactory trustManagerFactory;
        try {

            certificateFactory = CertificateFactory.getInstance("X.509");
            inputStream = context.getAssets().open("srca.cer");//这里导入SSL证书文件

            try {
                //读取证书
                cer = certificateFactory.generateCertificate(inputStream);
                L.i(cer.getPublicKey().toString());

            } finally {
                inputStream.close();
            }

            //创建一个证书库，并将证书导入证书库
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, null); //双向验证时使用
            keystore.setCertificateEntry("trust", cer);

            // 实例化信任库
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory
                    .getDefaultAlgorithm());
            // 初始化信任库
            trustManagerFactory.init(keystore);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

            return sslContext;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
