package com.example.administrator.myweather.internet;

import com.example.administrator.myweather.gson.CityBean;
import com.example.administrator.myweather.gson.CountyBean;
import com.example.administrator.myweather.gson.ProvinceBean;
import com.example.administrator.myweather.gson.WeatherBean;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengwuy on 2017/2/17.
 * Email: 963460692@qq.com
 * description: manager the retrofit
 */

public class RetrofitManager {

    private static RetrofitManager mRetrofitManager;
    private HttpService mHttpService;

    private RetrofitManager() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstant.URL).build();
        mHttpService = retrofit.create(HttpService.class);
    }

    public static RetrofitManager getIntance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    /**
     * 获取省份及ID
     * @param observer 回调
     */
    public void getlistProvince(Observer<List<ProvinceBean>> observer) {
        defaultSchedule(mHttpService.listProvince(), observer);
    }

    /**
     * 获取市及ID
     * @param provinceId 省ID
     * @param observer 回调
     */
    public void getListCity(String provinceId, Observer<List<CityBean>> observer) {
        defaultSchedule(mHttpService.listCity(provinceId), observer);
    }

    /**
     * 获取县及ID
     * @param provinceId 省份ID
     * @param cityId 市ID
     * @param observer 回调
     */
    public void getListCounty(String provinceId, String cityId, Observer<List<CountyBean>> observer) {
        defaultSchedule(mHttpService.listCounty(provinceId, cityId), observer);
    }

    /**
     * 获取天气信息
     * @param weatherId weatherId
     * @param observer 回调
     */
    public void getWeather(String weatherId, Observer<WeatherBean> observer) {
        defaultSchedule(mHttpService.getWeatherInfo(weatherId, ApiConstant.WEATHER_KRY), observer);
    }


    /**
     * 封装返回后的结果在主线程中调用
     * @param observable 网络线程
     * @param subscriber 网络返回的回调
     * @param <T> 泛型
     */
    private <T> void defaultSchedule(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
