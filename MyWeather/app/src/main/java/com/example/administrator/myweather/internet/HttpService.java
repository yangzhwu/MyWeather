package com.example.administrator.myweather.internet;

import com.example.administrator.myweather.gson.NowDataBean;
import com.example.administrator.myweather.gson.SuggestionDataBean;
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

    @GET("s6/weather/forecast")
    Observable<WeatherBean> getWeatherInfo(@Query("location") String location, @Query("key") String key);


    @GET("v5/suggestion")
    Observable<SuggestionDataBean> getSuggestionInfo(@Query("city") String location, @Query("key") String key);

    @GET("v5/now")
    Observable<NowDataBean> getNowInfo(@Query("city") String location, @Query("key") String key);
}
