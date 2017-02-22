package com.example.administrator.myweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.SharedPreferenceKeyConstant;
import com.example.administrator.myweather.gson.WeatherBean;
import com.example.administrator.myweather.internet.DefaultObserver;
import com.example.administrator.myweather.internet.RetrofitManager;
import com.example.administrator.myweather.util.ActivityUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static int REQUEST_CODE_FOR_CHOOSEAREA = 1;
    private Button mGetProvinceBtn;
    private Button mGetCityBtn;

    //默认北京
    private String mWeatherId = "CN101010100";

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
                ActivityUtil.goChooseAreaActivity(this, REQUEST_CODE_FOR_CHOOSEAREA);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FOR_CHOOSEAREA:
                if (resultCode == RESULT_OK) {
                    if (data != null && !mWeatherId.equals(data.getStringExtra(SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID))) {
                        mWeatherId = data.getStringExtra(SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID);
                        loadWeatherData();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadWeatherData() {
        RetrofitManager.getIntance().getWeather(mWeatherId, new DefaultObserver<WeatherBean>() {
                @Override
                public void onNext(WeatherBean weatherBeen) {
                    TextView textView = (TextView) findViewById(R.id.weather);
                    textView.setText(weatherBeen.getHeWeather().get(0).getStatus());
                }
            });
    }
}
