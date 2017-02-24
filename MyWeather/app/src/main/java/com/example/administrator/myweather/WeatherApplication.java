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
        initData();
        initLocation();
    }

    /**
     * 检测省份，市区，县等信息是否已经加载读取到数据库中，没有，则读取文件加载
     * 主要是为了减少api请求
     * 这三个信息是放在asserts文件中的
     */
    private void initData() {
        boolean hasLoadData = SharedPreferenceHelper.getInstance()
                .getBoolean(Constants.SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, false);
        if (!hasLoadData) {
            loadData();
        }
    }

    /**
     * 从文件中加载城市相关的数据
     */
    private void loadData() {
        CityDataLoad.loadCityData(this);
        SharedPreferenceHelper.getInstance().putBoolean(Constants.SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, true);
    }

    private void initLocation() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                    checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//            }
//        }
    }

    /**
     * 初始化高德地图，并且开启定位
     */
    private void startLocation() {
        final AMapLocationClient aMapLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setOnceLocation(true);
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setLocationCacheEnable(false);
        aMapLocationClient.setLocationOption(option);

        aMapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                //定位成功
                if (aMapLocation.getErrorCode() == 0) {
                    Log.e("City Code", aMapLocation.getCityCode() + " " +
                            aMapLocation.getDistrict() + " " +
                    aMapLocation.getAdCode());

                } else {
                    Toast.makeText(WeatherApplication.this, "location failed", Toast.LENGTH_SHORT).show();
                }
                aMapLocationClient.stopLocation();;
                aMapLocationClient.onDestroy();
            }
        });

        aMapLocationClient.startLocation();

    }
}
