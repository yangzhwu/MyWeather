package com.example.administrator.myweather.activity;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.Constants;
import com.example.administrator.myweather.db.CountyEntity;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.gson.WeatherBean;
import com.example.administrator.myweather.internet.DefaultObserver;
import com.example.administrator.myweather.internet.RetrofitManager;
import com.example.administrator.myweather.util.ActivityUtil;
import com.example.administrator.myweather.util.DialogUtil;
import com.example.administrator.myweather.util.LocationHelper;
import com.example.administrator.myweather.util.LogUtil;
import com.example.administrator.myweather.util.SharedPreferenceHelper;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, LocationHelper.LocationChangeListener{
    private final static int REQUEST_CODE_FOR_CHOOSEAREA = 1;
    private Button mGetProvinceBtn;
    private Button mGetCityBtn;

    //默认北京
    private String mWeatherId;
    private String mCityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationHelper.getInstance().registerLocationChangeListener(this);
        initView();
        initListener();
        initData();
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

    private void initData() {
        mWeatherId = SharedPreferenceHelper.getInstance().
                getString(Constants.SharedPreferenceKeyConstant.KEY_LAST_CHOOSE_WEATHER_ID, "CN101010100");
        mCityId = SharedPreferenceHelper.getInstance().
                getString(Constants.SharedPreferenceKeyConstant.KEY_LAST_CHOOSE_CITY_ID, "1");
        loadWeatherData();
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
                    if (data != null && !mWeatherId.equals(data.getStringExtra(Constants.SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID))) {
                        mWeatherId = data.getStringExtra(Constants.SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID);
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
                    Toast.makeText(MainActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                    TextView textView = (TextView) findViewById(R.id.weather);
                    textView.setText(weatherBeen.getHeWeather().get(0).getStatus());
                }
            });
    }

    @Override
    public void locationChange(AMapLocation aMapLocation) {
        String city = aMapLocation.getCity();
        LogUtil.e("City", city + " " + aMapLocation.getCityCode());
        if (city.contains("市")) {
            city = city.substring(0, city.length() - 1);
            List<CountyEntity> list = DBManager.getInstance(this).queryCountyListByCountyName(city);
            if (!list.isEmpty()) {
                final CountyEntity countyEntity = list.get(0);
                if (!countyEntity.getMCityId().equals(mCityId)) {
                    Dialog dialog = DialogUtil.creatChangeAreaDialog(this, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mWeatherId = countyEntity.getMWeatherId();
                            mCityId = countyEntity.getMCityId();
                            SharedPreferenceHelper.getInstance().putString(Constants.SharedPreferenceKeyConstant.KEY_LAST_CHOOSE_WEATHER_ID, mWeatherId);
                            SharedPreferenceHelper.getInstance().putString(Constants.SharedPreferenceKeyConstant.KEY_LAST_CHOOSE_CITY_ID, mCityId);
                            loadWeatherData();
                        }
                    });
                    dialog.show();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        LocationHelper.getInstance().unregisterLocationChangeListener(this);
        super.onDestroy();
    }
}
