package com.example.administrator.myweather;

import android.app.Application;

import com.example.administrator.myweather.constant.SharedPreferenceKeyConstant;
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
    }

    /**
     * 检测省份，市区，县等信息是否已经加载读取到数据库中，没有，则读取文件加载
     * 主要是为了减少api请求
     * 这三个信息是放在asserts文件中的
     */
    private void initData() {
        boolean hasLoadData = SharedPreferenceHelper.getInstance()
                .getBoolean(SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, false);
        if (!hasLoadData) {
            loadData();
        }
    }

    private void loadData() {
        CityDataLoad.loadCityData(this);
        SharedPreferenceHelper.getInstance().putBoolean(SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, true);

    }
}
