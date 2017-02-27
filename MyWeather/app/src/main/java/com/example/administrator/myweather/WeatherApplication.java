package com.example.administrator.myweather;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.administrator.myweather.constant.Constants;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.util.CityDataLoad;
import com.example.administrator.myweather.util.LocationHelper;
import com.example.administrator.myweather.util.SharedPreferenceHelper;


/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description:
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DBManager.init(this);
        SharedPreferenceHelper.init(this);
        LocationHelper.init(this);
    }
}
