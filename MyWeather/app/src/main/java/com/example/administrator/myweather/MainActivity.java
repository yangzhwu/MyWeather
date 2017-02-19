package com.example.administrator.myweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myweather.gson.CityBean;
import com.example.administrator.myweather.gson.ProvinceBean;
import com.example.administrator.myweather.internet.DefaultObserver;
import com.example.administrator.myweather.internet.RetrofitManager;
import com.example.administrator.myweather.util.ActivityUtil;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_province:
                ActivityUtil.goChooseProvinceActivity(this);
//                getProvinceData();
                break;
            case R.id.get_city:
//                getCityData();
                break;
            default:
                break;
        }
    }

    /**
     * 获取省份及ID
     */
    private void getProvinceData() {
        RetrofitManager.getIntance().getlistProvince(new DefaultObserver<List<ProvinceBean>>() {
            @Override
            public void onNext(List<ProvinceBean> provinceBeenList) {
                for (ProvinceBean provinceBean : provinceBeenList) {
                    Log.e(TAG, provinceBean.getId() + provinceBean.getName());
                }
                mGetProvinceBtn.setText(provinceBeenList.get(0).getName());
            }
        });
    }

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
