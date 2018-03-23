package com.example.administrator.myweather;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.administrator.myweather.constant.Constants;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.util.CityDataLoad;
import com.example.administrator.myweather.util.FileUtil;
import com.example.administrator.myweather.util.LocationHelper;
import com.example.administrator.myweather.util.LogUtil;
import com.example.administrator.myweather.util.SharedPreferenceHelper;
import com.umeng.analytics.MobclickAgent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description:
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferenceHelper.init(this);
        LocationHelper.init(this);
        FileUtil.init(this);

        if (BuildConfig.DEBUG) {
            LogUtil.setLogLeve(LogUtil.LOG_LEVEL_D);
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().build());
        }

        //设置为普通统计场景
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType. E_UM_NORMAL);
    }
}
