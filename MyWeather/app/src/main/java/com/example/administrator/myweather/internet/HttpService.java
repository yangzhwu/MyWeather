package com.example.administrator.myweather.internet;

import com.example.administrator.myweather.gson.CityBean;
import com.example.administrator.myweather.gson.CountyBean;
import com.example.administrator.myweather.gson.ProvinceBean;
import com.example.administrator.myweather.gson.WeatherBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description: all http api in there, use retrofit
 */

public interface HttpService {

    @GET("china")
    Observable<List<ProvinceBean>> listProvince();

    @GET("china/{provinceId}")
    Observable<List<CityBean>> listCity(@Path("provinceId") String id);

    @GET("china/{provinceId}/{cityId}")
    Observable<List<CountyBean>> listCounty(@Path("provinceId") String provinceId, @Path("cityId") String cityId);

    @GET("weather")
    Observable<List<WeatherBean>> getWeatherInfo(@Query("cityid") String cityId, @Query("key") String key);
}
