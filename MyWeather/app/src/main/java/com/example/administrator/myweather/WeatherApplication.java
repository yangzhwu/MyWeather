package com.example.administrator.myweather;

import android.app.Application;
import android.util.Log;

import com.example.administrator.myweather.constant.SharedPreferenceKeyConstant;
import com.example.administrator.myweather.db.CityEntity;
import com.example.administrator.myweather.db.CountyEntity;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.db.ProvinceEntity;
import com.example.administrator.myweather.gson.CityBean;
import com.example.administrator.myweather.gson.CountyBean;
import com.example.administrator.myweather.gson.ProvinceBean;
import com.example.administrator.myweather.internet.DefaultObserver;
import com.example.administrator.myweather.internet.RetrofitManager;
import com.example.administrator.myweather.util.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description:
 */

public class WeatherApplication extends Application {
    private static final String TAG = "WeatherApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        DBManager.init(this);
        SharedPreferenceHelper.init(this);
        initData();
    }

    private void initData() {
        boolean hasLoadProvinceData = SharedPreferenceHelper.getInstance()
                .getBoolean(SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, false);
        if (!hasLoadProvinceData) {
            loadProvinceData();
        }
    }

    private List<ProvinceEntity> mProvinceEntityList = new ArrayList<>();
    private void loadProvinceData() {
        RetrofitManager.getIntance().getlistProvince(new DefaultObserver<List<ProvinceBean>>() {
            @Override
            public void onNext(List<ProvinceBean> provinceBeenList) {
                for (ProvinceBean provinceBean : provinceBeenList) {
                    ProvinceEntity provinceEntity = new ProvinceEntity();
                    provinceEntity.setMProvinceId(String.valueOf(provinceBean.getId()));
                    provinceEntity.setMProvinceName(provinceBean.getName());
                    mProvinceEntityList.add(provinceEntity);
                }
                loadCityData();
            }
        });
    }

    private List<CityEntity> mCityEntityList = new ArrayList<>();
    private int mHasLoad;
    private void loadCityData() {
        mHasLoad = 0;
        for (final ProvinceEntity  provinceEntity : mProvinceEntityList) {
            RetrofitManager.getIntance().getListCity(provinceEntity.getMProvinceId(), new DefaultObserver<List<CityBean>>() {
                @Override
                public void onNext(List<CityBean> cityBeenList) {
                    mHasLoad++;
                    Log.e("demaxiya", " " + mHasLoad + provinceEntity.getMProvinceId());
                    for (CityBean cityBean : cityBeenList) {
                        CityEntity cityEntity = new CityEntity();
                        cityEntity.setMProvinceId(provinceEntity.getMProvinceId());
                        cityEntity.setMCityId(String.valueOf(cityBean.getId()));
                        cityEntity.setMCityName(cityBean.getName());
                        mCityEntityList.add(cityEntity);
                    }
                    if (mHasLoad == mProvinceEntityList.size()) {
                        loadCountyData();
                    }
                }
            });
        }
    }

    private List<CountyEntity> mCountyEntityList = new ArrayList<>();
    private void loadCountyData() {
        mHasLoad = 0;
        for (final CityEntity cityEntity : mCityEntityList) {
            RetrofitManager.getIntance().getListCounty(cityEntity.getMProvinceId(), cityEntity.getMCityId(), new DefaultObserver<List<CountyBean>>() {
                @Override
                public void onNext(List<CountyBean> countyBeenList) {
                    mHasLoad++;
                    for (CountyBean countyBean : countyBeenList) {
                        CountyEntity countyEntity = new CountyEntity();
                        countyEntity.setMCityId(cityEntity.getMCityId());
                        countyEntity.setMCountyId(countyEntity.getMCountyId());
                        countyEntity.setMCountyName(countyBean.getName());
                        countyEntity.setMWeatherId(countyBean.getWeather_id());
                        mCountyEntityList.add(countyEntity);
                    }
                    if (mHasLoad == mCityEntityList.size()) {
                        storeToDatabase();
                    }
                }
            });
        }
    }

    private void storeToDatabase() {
        Log.e(TAG, "load cpmplete  " + mProvinceEntityList.size());
        DBManager.getInstance().insertProvinceList(mProvinceEntityList);
        DBManager.getInstance().insertCityList(mCityEntityList);
        DBManager.getInstance().insertCountyList(mCountyEntityList);
        SharedPreferenceHelper.getInstance().putBoolean(SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, true);
    }
}
