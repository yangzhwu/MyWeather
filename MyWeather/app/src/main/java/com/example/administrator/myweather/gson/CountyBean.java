package com.example.administrator.myweather.gson;

import java.io.Serializable;

/**
 * Created by zhengwuy on 2017/2/18.
 * Email: 963460692@qq.com
 * description: 县id
 */

public class CountyBean implements Serializable {
    private int id;
    private String name;
    private String weather_id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getWeather_id() {
        return weather_id;
    }
}
