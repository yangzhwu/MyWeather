package com.example.administrator.myweather.internet;

import com.example.administrator.myweather.gson.WeatherBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description: all http api in there, use retrofit
 */

public interface HttpService {

    @GET("weather")
    Observable<WeatherBean> getWeatherInfo(@Query("cityid") String cityId, @Query("key") String key);
}
