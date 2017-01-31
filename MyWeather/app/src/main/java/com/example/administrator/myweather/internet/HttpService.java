package com.example.administrator.myweather.internet;

import com.example.administrator.myweather.gson.ProvinceBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by zhengwuy on 2017/1/31.
 * Email: 963460692@qq.com
 * description: all http api in there, use retrofit
 */

public interface HttpService {

    @GET("china")
    Observable<List<ProvinceBean>> listProvince();
}
