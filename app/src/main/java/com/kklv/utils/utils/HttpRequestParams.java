package com.kklv.utils.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class HttpRequestParams{
    public final List<KeyValue> mParamsList=new ArrayList<>();

    public void addParams(String key,String value){
        if(!TextUtils.isEmpty(key)){
            mParamsList.add(new KeyValue(key,value));
        }
    }

    public class KeyValue{
        private String key;
        private String value;

        private KeyValue(String key,String value){
            this.key=key;
            this.value=value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

}
