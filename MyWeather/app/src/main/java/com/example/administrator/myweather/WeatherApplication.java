package com.example.administrator.myweather;

import android.app.Application;

import com.example.administrator.myweather.internet.RetrofitUtil;

/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description:
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        RetrofitUtil.init();
        super.onCreate();
    }
}
