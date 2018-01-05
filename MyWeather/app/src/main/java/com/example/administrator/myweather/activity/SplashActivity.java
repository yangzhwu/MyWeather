package com.example.administrator.myweather.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.myweather.LocalCache.DataCache;
import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.Constants;
import com.example.administrator.myweather.gson.NowDataBean;
import com.example.administrator.myweather.gson.SuggestionDataBean;
import com.example.administrator.myweather.gson.WeatherBean;
import com.example.administrator.myweather.rxjava.ErrorCompleteObserver;
import com.example.administrator.myweather.util.ActivityUtil;
import com.example.administrator.myweather.util.CityDataLoad;
import com.example.administrator.myweather.util.FileUtil;
import com.example.administrator.myweather.util.LocationHelper;
import com.example.administrator.myweather.util.LogUtil;
import com.example.administrator.myweather.util.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private static final int REQUEST_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initData();
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
        } else {
            boolean isLoadWeather = DataCache.getInstance().getLoad();
            if (!isLoadWeather) {
                loadWeatherData();
            } else {
                startLocation();
            }
        }
    }

    /**
     * 从文件中加载城市相关的数据
     */
    private void loadData() {
        LogUtil.d(TAG, "开始加载本地数据");
        CityDataLoad.loadCityData(this, new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                LogUtil.e(TAG, "onNext");

            }

            @Override
            public void onError(Throwable e) {
               LogUtil.e(TAG, e.toString());
            }

            @Override
            public void onComplete() {
                SharedPreferenceHelper.getInstance().putBoolean(Constants.SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, true);
                startLocation();
                LogUtil.e(TAG, "onComplete");

            }
        });
//        Observable.zip(CityDataLoad.loadCityData(this), FileUtil.readBeanInfo(NowDataBean.class), FileUtil.readBeanInfo(SuggestionDataBean.class), FileUtil.readBeanInfo(WeatherBean.class),
//                (aBoolean, nowDataBean, suggestionDataBean, weatherBean) -> {
//                    LogUtil.d(TAG, "加载本地数据完成");
//                    SharedPreferenceHelper.getInstance().putBoolean(Constants.SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, true);
//                    setCache(nowDataBean, suggestionDataBean, weatherBean);
//                    startLocation();
//                    return true;
//                }).subscribe();
    }

    private void loadWeatherData() {
        Observable.zip(FileUtil.readBeanInfo(NowDataBean.class), FileUtil.readBeanInfo(SuggestionDataBean.class), FileUtil.readBeanInfo(WeatherBean.class),
                (nowDataBean, suggestionDataBean, weatherBean) -> {
                    setCache(nowDataBean, suggestionDataBean, weatherBean);
                    startLocation();
                    return true;
                }).subscribe();
    }

    private void setCache(NowDataBean nowDataBean, SuggestionDataBean suggestionDataBean, WeatherBean weatherBean) {
        DataCache.getInstance().setNowDataBean(nowDataBean);
        DataCache.getInstance().setSuggestionDataBean(suggestionDataBean);
        DataCache.getInstance().setWeatherBean(weatherBean);
        DataCache.getInstance().setLoad(true);
    }

    /**
     * 初始化定位，android6.0首先应该先判断权限，已经申请过的权限不需要再次申请
     */
    private void startLocation() {
        List<String> permissionList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (!permissionList.isEmpty()) {
                requestPermissions(permissionList.toArray(new String[permissionList.size()]), REQUEST_PERMISSION_CODE);
            } else {
                //开始定位
                LocationHelper.getInstance().startLocation();
                startMainActivity();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        //没有授权，取消定位
                        Log.e("location", "no permission");
                        return;
                    }
                }
                //// TODO: 2017/2/27 开始定位
                Log.e("location", "start_location");
                LocationHelper.getInstance().startLocation();
                startMainActivity();
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startMainActivity() {
        ActivityUtil.goHomeActivity(this);
        finish();
        ActivityUtil.finishAnim(this);
    }
}
