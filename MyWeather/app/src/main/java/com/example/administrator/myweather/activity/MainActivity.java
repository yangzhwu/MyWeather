package com.example.administrator.myweather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.WeatherApplication;
import com.example.administrator.myweather.db.CityEntity;
import com.example.administrator.myweather.db.CountyEntity;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.db.ProvinceEntity;
import com.example.administrator.myweather.gson.CityBean;
import com.example.administrator.myweather.gson.ProvinceBean;
import com.example.administrator.myweather.internet.DefaultObserver;
import com.example.administrator.myweather.internet.RetrofitManager;
import com.example.administrator.myweather.util.ActivityUtil;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "MainActivity";
    private Button mGetProvinceBtn;
    private Button mGetCityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mGetProvinceBtn = (Button) findViewById(R.id.get_province);
        mGetCityBtn = (Button) findViewById(R.id.get_city);

    }

    private void initListener() {
        mGetProvinceBtn.setOnClickListener(this);
        mGetCityBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_province:
                ActivityUtil.goChooseProvinceActivity(this, ChooseProvinceActivity.CHOOSE_TYPE_PROVINCE, "");
//                getProvinceData();
                break;
            case R.id.get_city:
//                writeData();
//                getCityData();
                break;
            default:
                break;
        }
    }

//    private void writeData() {
//        Log.e(TAG, "start write");
//        WeatherApplication application = (WeatherApplication) getApplication();
//        if (application.getFile() != null) {
//            Log.e(TAG, "file is not empty");
//            File file = application.getFile();
//            if (file.exists()) {
//                file.delete();
//            }
//            try {
//                if (file.createNewFile()) {
//                    Log.e(TAG, "file created sucess");
//                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
//                    List<ProvinceEntity> list = DBManager.getInstance().queryProvinceList();
//                    StringBuilder stringBuilder = new StringBuilder();
//                    for (ProvinceEntity provinceEntity : list) {
//                        stringBuilder.append("{id:" + provinceEntity.getId()
//                                + ",mProvinceId:" + provinceEntity.getMProvinceId()
//                                + ",mProvinceName:" + provinceEntity.getMProvinceName() + "}" );
//                        bufferedWriter.write(stringBuilder.toString());
//                        bufferedWriter.flush();
//                        bufferedWriter.newLine();
//                        stringBuilder.delete(0, stringBuilder.length());
//                    }
//                    bufferedWriter.close();
//
//                }
//            } catch (IOException e) {
//                Log.e(TAG, "create file failed");
//                e.printStackTrace();
//            }
//
//            File fileCity = new File(application.getExternalCacheDir(), "city.txt");
//            if (fileCity.exists()) {
//                fileCity.delete();
//            }
//            try {
//                fileCity.createNewFile();
//                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileCity));
//                List<CityEntity> list = DBManager.getInstance().queryCityList();
//                StringBuilder stringBuilder = new StringBuilder();
//                for (CityEntity cityEntity : list) {
//                    stringBuilder.append("{id:" + cityEntity.getId()
//                            + ",mCityId:" + cityEntity.getMCityId()
//                            + ",mCityName:" + cityEntity.getMCityName()
//                            + ",mProvinceId:" + cityEntity.getMProvinceId() + "}");
//                    bufferedWriter.write(stringBuilder.toString());
//                    bufferedWriter.flush();
//                    bufferedWriter.newLine();
//                    stringBuilder.delete(0, stringBuilder.length());
//                }
//                bufferedWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            File fileCounty = new File(application.getExternalCacheDir(), "county.txt");
//            if (fileCounty.exists()) {
//                fileCounty.delete();
//            }
//            try {
//                fileCounty.createNewFile();
//                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileCounty));
//                List<CountyEntity> list = DBManager.getInstance().queryCountyList();
//                StringBuilder stringBuilder = new StringBuilder();
//                for (CountyEntity countyEntity : list) {
//                    stringBuilder.append("{id:" + countyEntity.getId()
//                            + ",mCountyId:" + countyEntity.getMCountyId()
//                            + ",mCountyName:" + countyEntity.getMCountyName()
//                            + ",mWeatherId:" + countyEntity.getMWeatherId()
//                            + ",mCityId:" + countyEntity.getMCityId() + "}");
//                    bufferedWriter.write(stringBuilder.toString());
//                    bufferedWriter.flush();
//                    bufferedWriter.newLine();
//                    stringBuilder.delete(0, stringBuilder.length());
//                }
//                bufferedWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
//            Log.e(TAG, "file is null");
//        }
//    }
//
//    /**
//     * 获取省份及ID
//     */
//    private void getProvinceData() {
//        RetrofitManager.getIntance().getlistProvince(new DefaultObserver<List<ProvinceBean>>() {
//            @Override
//            public void onNext(List<ProvinceBean> provinceBeenList) {
//                for (ProvinceBean provinceBean : provinceBeenList) {
//                    Log.e(TAG, provinceBean.getId() + provinceBean.getName());
//                }
//                mGetProvinceBtn.setText(provinceBeenList.get(0).getName());
//            }
//        });
//    }

    /**
     * 获取市及ID
     */
    private void getCityData() {
        RetrofitManager.getIntance().getListCity("22", new DefaultObserver<List<CityBean>>() {
            @Override
            public void onNext(List<CityBean> cityBeenList) {
                mGetCityBtn.setText(cityBeenList.get(0).getId());
            }
        });
    }

    private void getCountyData() {

    }
}
