package com.example.administrator.myweather.gson;

import java.io.Serializable;

/**
 * Created by zhengwuy on 2017/2/18.
 * Email: 963460692@qq.com
 * description:
 */

public class CityBean implements Serializable {
    private int id;
    private String name;
    private int province_id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
