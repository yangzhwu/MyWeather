package com.example.administrator.myweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.example.administrator.myweather.LocalCache.DataCache;
import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.Constants;
import com.example.administrator.myweather.gson.NowDataBean;
import com.example.administrator.myweather.gson.SuggestionDataBean;
import com.example.administrator.myweather.gson.WeatherBean;
import com.example.administrator.myweather.internet.DefaultObserver;
import com.example.administrator.myweather.internet.RetrofitManager;
import com.example.administrator.myweather.rxjava.ErrorCompleteObserver;
import com.example.administrator.myweather.util.FileUtil;
import com.example.administrator.myweather.util.LocationHelper;
import com.example.administrator.myweather.util.LogUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener, LocationHelper.LocationChangeListener{
    private final static int REQUEST_CODE_FOR_CHOOSEAREA = 1;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String mLocation;
    private AppBarLayout mAppBarLayout;

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
            actionBar.setHomeAsUpIndicator(R.drawable.setting_icon);
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        DrawerLayout.DrawerListener drawerListener = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerListener);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_layout);
        mCollapsingToolbarLayout.setTitle("");

        mSwipeRefreshLayout = findViewById(R.id.swip_refresh_layout);
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
        mSwipeRefreshLayout.setProgressViewOffset(true, 100, 300);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimaryDark);

        mAppBarLayout = findViewById(R.id.app_bar_layout);
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> loadWeatherData(mLocation));

        //toolbar折叠的时候才开启下拉刷新
        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        });

    }

    private void initData() {
        showNow(DataCache.getInstance().getNowDataBean());
        showSuggestion(DataCache.getInstance().getSuggestionDataBean());
        showWeather(DataCache.getInstance().getWeatherBean());
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
//        switch (v.getId()) {
//            case R.id.get_province:
//                ActivityUtil.goChooseAreaActivity(this, REQUEST_CODE_FOR_CHOOSEAREA);
////                getProvinceData();
//                break;
//            case R.id.get_city:
////                writeData();
////                getCityData();
//                break;
//            default:
//                break;
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FOR_CHOOSEAREA:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        mLocation = data.getStringExtra(Constants.SharedPreferenceKeyConstant.KEY_CHOOSE_COUNTY_WEATHER_ID);
                        loadWeatherData(mLocation);
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadWeatherData(String location) {
        LogUtil.e("1111", "loadweatherdata");
        RetrofitManager retrofitManager = RetrofitManager.getIntance();
        Observable.zip(retrofitManager.getWeather(location), retrofitManager.getSuggestion(location), retrofitManager.getNow(location),
                new Function3<WeatherBean, SuggestionDataBean, NowDataBean, Boolean>() {
            @Override
            public Boolean apply(WeatherBean weatherBean, SuggestionDataBean suggestionDataBean, NowDataBean nowDataBean) throws Exception {
                FileUtil.saveBeanInfo(weatherBean.getClass(), weatherBean);
                FileUtil.saveBeanInfo(suggestionDataBean.getClass(), suggestionDataBean);
                FileUtil.saveBeanInfo(nowDataBean.getClass(), nowDataBean);
                showWeather(weatherBean);
                showSuggestion(suggestionDataBean);
                showNow(nowDataBean);
                return true;
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorCompleteObserver<Boolean>() {

            @Override
            public void onNext(Boolean aBoolean) {
                LogUtil.e("1111", "onNext");
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("1111", "onError" + e.toString());
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                LogUtil.e("1111", "onComplete");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
//        RetrofitManager.getIntance().getWeather(location, new DefaultObserver<WeatherBean>() {
//                @Override
//                public void onNext(WeatherBean weatherBean) {
//                    if (weatherBean != null) {
//                        FileUtil.saveBeanInfo(weatherBean.getClass(), weatherBean);
//                        showWeather(weatherBean);
//                    }
//                }
//            });
//        RetrofitManager.getIntance().getSuggestion(location, new DefaultObserver<SuggestionDataBean>() {
//            @Override
//            public void onNext(SuggestionDataBean suggestionDataBean) {
//                if (suggestionDataBean != null) {
//                    FileUtil.saveBeanInfo(suggestionDataBean.getClass(), suggestionDataBean);
//                    showSuggestion(suggestionDataBean);
//                }
//            }
//        });
//
//        RetrofitManager.getIntance().getNow(location, new DefaultObserver<NowDataBean>() {
//            @Override
//            public void onNext(NowDataBean nowDataBean) {
//                if (nowDataBean != null) {
//                    FileUtil.saveBeanInfo(nowDataBean.getClass(), nowDataBean);
//                    showNow(nowDataBean);
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
//            }
//        });
    }

    /**
     * 显示结果
     * @param weatherBean 天气结果
     */
    private void showWeather(WeatherBean weatherBean) {
        if (weatherBean != null) {
            LinearLayout forecast_layout = findViewById(R.id.forecast_layout);
            List<WeatherBean.HeWeather6Bean> heWeather6BeanList = weatherBean.getHeWeather6();
            for (WeatherBean.HeWeather6Bean heWeather6Bean : heWeather6BeanList) {
                //填充未来天气数据
                List<WeatherBean.HeWeather6Bean.DailyForecastBean> dailyForecastBeanList = heWeather6Bean.getDaily_forecast();
                for (WeatherBean.HeWeather6Bean.DailyForecastBean dailyForecastBean : dailyForecastBeanList) {
                    LinearLayout forecast_item = (LinearLayout) LayoutInflater.from(MainActivity.this)
                            .inflate(R.layout.forecast_item_layout, forecast_layout, false);
                    TextView data_text = forecast_item.findViewById(R.id.data_text);
                    TextView info_text = forecast_item.findViewById(R.id.info_text);
                    TextView max_text = forecast_item.findViewById(R.id.max_text);
                    TextView min_text = forecast_item.findViewById(R.id.min_text);
                    data_text.setText(dailyForecastBean.getDate());
                    info_text.setText(dailyForecastBean.getCond_txt_n());
                    max_text.setText(dailyForecastBean.getTmp_max());
                    min_text.setText(dailyForecastBean.getTmp_min());
                    forecast_layout.addView(forecast_item);

                }
            }
        }
    }

    private void showSuggestion(SuggestionDataBean suggestionDataBean) {
        if (suggestionDataBean != null) {
            List<SuggestionDataBean.HeWeather5Bean> heWeather5Beans = suggestionDataBean.getHeWeather5();
            if (heWeather5Beans != null && !heWeather5Beans.isEmpty()) {
                SuggestionDataBean.HeWeather5Bean heWeather5Bean = heWeather5Beans.get(0);
                if (heWeather5Bean.isSucess()) {
                    SuggestionDataBean.HeWeather5Bean.SuggestionBean suggestionBean = heWeather5Bean.getSuggestion();
                    if (suggestionBean != null) {
                        SuggestionDataBean.HeWeather5Bean.SuggestionBean.ComfBean comfBean = suggestionBean.getComf();
                        if (comfBean != null) {
                            TextView comfort_text = findViewById(R.id.comfort_text);
                            comfort_text.setText(String.format("舒适指数：%s，%s", comfBean.getBrf(), comfBean.getTxt()));
                        }

                        SuggestionDataBean.HeWeather5Bean.SuggestionBean.CwBean cwBean = suggestionBean.getCw();
                        if (cwBean != null) {
                            TextView car_wash_text = findViewById(R.id.car_wash_text);
                            car_wash_text.setText(String.format("洗车指数：%s，%s", cwBean.getBrf(), cwBean.getTxt()));
                        }

                        SuggestionDataBean.HeWeather5Bean.SuggestionBean.DrsgBean drsgBean = suggestionBean.getDrsg();
                        if (drsgBean != null) {
                            TextView drsg_text = findViewById(R.id.drsg_text);
                            drsg_text.setText(String.format("穿衣指数：%s，%s", drsgBean.getBrf(), drsgBean.getTxt()));
                        }

                        SuggestionDataBean.HeWeather5Bean.SuggestionBean.FluBean fluBean = suggestionBean.getFlu();
                        if (fluBean != null) {
                            TextView flu_text = findViewById(R.id.flu_text);
                            flu_text.setText(String.format("感冒指数：%s，%s", fluBean.getBrf(), fluBean.getTxt()));
                        }

                        SuggestionDataBean.HeWeather5Bean.SuggestionBean.SportBean sportBean = suggestionBean.getSport();
                        if (sportBean != null) {
                            TextView sport_text = findViewById(R.id.sport_text);
                            sport_text.setText(String.format("运动指数：%s，%s", sportBean.getBrf(), sportBean.getTxt()));
                        }

                        SuggestionDataBean.HeWeather5Bean.SuggestionBean.TravBean travBean = suggestionBean.getTrav();
                        if (travBean != null) {
                            TextView trav_text = findViewById(R.id.trav_text);
                            trav_text.setText(String.format("旅游指数：%s，%s", travBean.getBrf(), travBean.getTxt()));
                        }

                        SuggestionDataBean.HeWeather5Bean.SuggestionBean.UvBean uvBean = suggestionBean.getUv();
                        if (uvBean != null) {
                            TextView uv_text = findViewById(R.id.uv_text);
                            uv_text.setText(String.format("紫外线指数指数：%s，%s", uvBean.getBrf(), uvBean.getTxt()));
                        }

                    }
                }
            }
        }
    }

    private void showNow(NowDataBean nowDataBean) {
        if (nowDataBean != null) {
            TextView temp_text = findViewById(R.id.temp_text);
            TextView weather_text = findViewById(R.id.weather_text);
            List<NowDataBean.HeWeather5Bean> heWeather5Beans = nowDataBean.getHeWeather5();
            if (heWeather5Beans != null && !heWeather5Beans.isEmpty()) {
                NowDataBean.HeWeather5Bean heWeather5Bean = heWeather5Beans.get(0);
                if (heWeather5Bean != null) {
                    if (heWeather5Bean.isSucess()) {
                        temp_text.setText(String.format("%s°C", heWeather5Bean.getNow().getTmp()));
                        weather_text.setText(heWeather5Bean.getNow().getCond().getTxt());
                    } else {
                        Toast.makeText(MainActivity.this, heWeather5Bean.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


    @Override
    public void locationSucess(AMapLocation aMapLocation) {
        //获取经纬度
        double lat = aMapLocation.getLatitude();
        double lng = aMapLocation.getLongitude();
        mCollapsingToolbarLayout.setTitle(aMapLocation.getCity());
        mLocation = String.valueOf(lat) + ","+ String.valueOf(lng);
        loadWeatherData(mLocation);
    }

    @Override
    public void locationFailed(AMapLocation aMapLocation) {
         Toast.makeText(this, "定位失败，请选择城市", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        LocationHelper.getInstance().unregisterLocationChangeListener(this);
        super.onDestroy();
    }
}
