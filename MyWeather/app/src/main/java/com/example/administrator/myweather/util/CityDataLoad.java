package com.example.administrator.myweather.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.myweather.constant.Constants;
import com.example.administrator.myweather.db.CityEntity;
import com.example.administrator.myweather.db.CountyEntity;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.db.ProvinceEntity;
import com.example.administrator.myweather.rxjava.ErrorCompleteObserver;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhengwuy on 2017/2/20.
 * Emali: 963460692@qq.com
 * description:
 */

public class CityDataLoad {
    private final static String TAG = "CityDataLoad";


    public static void loadCityData(Context context) {
        Observable.create((ObservableOnSubscribe<Boolean>) e -> {
            loadData(context);
            e.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorCompleteObserver<Boolean>() {
            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "加载城市信息到数据库失败");
            }

            @Override
            public void onComplete() {
                SharedPreferenceHelper.getInstance()
                        .putBoolean(Constants.SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, true);
                LogUtil.d(TAG, "加载城市信息到数据库成功");

            }
        });
    }


    /**
     * 加载城市信息到数据库的具体逻辑
     * @param context 上下文
     * @throws Exception 异常
     */
    private static void loadData(Context context) throws Exception{
        AssetManager assetManager = context.getAssets();
        Gson gson = new Gson();

        InputStream inputStreamProvince = assetManager.open(Constants.FileConstant.PROVINCE_FILE_NAME);
        InputStreamReader inputStreamReaderProvince = new InputStreamReader(inputStreamProvince);
        BufferedReader bufferedReaderProvince = new BufferedReader(inputStreamReaderProvince);
        String contentProvince;
        List<ProvinceEntity> provinceEntityList = new ArrayList<>();
        while (!TextUtils.isEmpty(contentProvince = bufferedReaderProvince.readLine())) {
            ProvinceEntity provinceEntity = gson.fromJson(contentProvince, ProvinceEntity.class);
            provinceEntityList.add(provinceEntity);
        }
        DBManager.getInstance(context).insertProvinceList(provinceEntityList);

        InputStream inputStreamCity = assetManager.open(Constants.FileConstant.CITY_FILE_NAME);
        InputStreamReader inputStreamReaderCity = new InputStreamReader(inputStreamCity);
        BufferedReader bufferedReaderCity = new BufferedReader((inputStreamReaderCity));
        String contentCity;
        List<CityEntity> cityEntityList = new ArrayList<>();
        while (!TextUtils.isEmpty(contentCity = bufferedReaderCity.readLine())) {
            CityEntity cityEntity = gson.fromJson(contentCity, CityEntity.class);
            cityEntityList.add(cityEntity);
        }
        DBManager.getInstance(context).insertCityList(cityEntityList);

        InputStream inputStreamCounty = assetManager.open(Constants.FileConstant.COUNTY_FILE_NAME);
        InputStreamReader inputStreamReaderCounty = new InputStreamReader(inputStreamCounty);
        BufferedReader bufferedReaderCounty = new BufferedReader((inputStreamReaderCounty));
        String contentCounty;
        List<CountyEntity> countyEntityList = new ArrayList<>();
        while (!TextUtils.isEmpty(contentCounty = bufferedReaderCounty.readLine())) {
            CountyEntity countyEntity = gson.fromJson(contentCounty, CountyEntity.class);
            countyEntityList.add(countyEntity);
        }
        DBManager.getInstance(context).insertCountyList(countyEntityList);
    }
}
