package com.example.administrator.myweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.SharedPreferenceKeyConstant;
import com.example.administrator.myweather.gson.WeatherBean;
import com.example.administrator.myweather.internet.DefaultObserver;
import com.example.administrator.myweather.internet.RetrofitManager;
import com.example.administrator.myweather.util.ActivityUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener{
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        mGetProvinceBtn = (Button) findViewById(R.id.get_province);
        mGetCityBtn = (Button) findViewById(R.id.get_city);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        DrawerLayout.DrawerListener drawerListener = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerListener);


    }

    private void initListener() {
        mGetProvinceBtn.setOnClickListener(this);
        mGetCityBtn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
