package com.example.administrator.myweather.internet;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description:对retrofit进行全局配置
 */

public class RetrofitUtil {
    private static HttpService mHttpService;

    public static void init() {
        if (mHttpService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstant.URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mHttpService = retrofit.create(HttpService.class);
        }
    }

    public static HttpService getHttpService() {
        return mHttpService;
    }
}
