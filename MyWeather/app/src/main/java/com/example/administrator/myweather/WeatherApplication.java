package com.example.administrator.myweather;

import android.app.Application;
import android.os.StrictMode;

import com.example.administrator.myweather.util.FileUtil;
import com.example.administrator.myweather.util.LocationHelper;
import com.example.administrator.myweather.util.LogUtil;
import com.example.administrator.myweather.util.SharedPreferenceHelper;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description:
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType. E_UM_NORMAL);
        if (BuildConfig.DEBUG) {
            LogUtil.setLogLeve(LogUtil.LOG_LEVEL_D);
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().build());
        }

        SharedPreferenceHelper.init(this);
        LocationHelper.init(this);
        FileUtil.init(this);
    }
}
