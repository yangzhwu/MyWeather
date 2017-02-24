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
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengwuy on 2017/2/20.
 * Emali: 963460692@qq.com
 * description:
 */

public class CityDataLoad {
    private final static String TAG = "CityDataLoad";


    public static void loadCityData(Context context) {
        AssetManager assetManager = context.getAssets();
        Gson gson = new Gson();

        //读取省份数据
        try {
            InputStream inputStream = assetManager.open(Constants.FileConstant.PROVINCE_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
            String content;
            List<ProvinceEntity> provinceEntityList = new ArrayList<>();
            while (!TextUtils.isEmpty(content = bufferedReader.readLine())) {
                ProvinceEntity provinceEntity = gson.fromJson(content, ProvinceEntity.class);
                provinceEntityList.add(provinceEntity);
            }
            DBManager.getInstance().insertProvinceList(provinceEntityList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取市数据
        try {
            InputStream inputStream = assetManager.open(Constants.FileConstant.CITY_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
            String content;
            List<CityEntity> cityEntityList = new ArrayList<>();
            while (!TextUtils.isEmpty(content = bufferedReader.readLine())) {
                CityEntity cityEntity = gson.fromJson(content, CityEntity.class);
                cityEntityList.add(cityEntity);
            }
            DBManager.getInstance().insertCityList(cityEntityList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取县数据
        try {
            InputStream inputStream = assetManager.open(Constants.FileConstant.COUNTY_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
            String content;
            List<CountyEntity> countyEntityList = new ArrayList<>();
            while (!TextUtils.isEmpty(content = bufferedReader.readLine())) {
                CountyEntity countyEntity = gson.fromJson(content, CountyEntity.class);
                countyEntityList.add(countyEntity);
            }
            DBManager.getInstance().insertCountyList(countyEntityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "load complete");
    }
}
