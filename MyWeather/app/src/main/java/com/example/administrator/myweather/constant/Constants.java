package com.example.administrator.myweather.constant;

/**
 * Created by zhengwuy on 2017/2/20.
 * Emali: 963460692@qq.com
 * description:常量类
 */

public class Constants {

    /**
     * Created by zhengwuy on 2017/2/20.
     * Emali: 963460692@qq.com
     * description:存储文件相关的常量
     */

    public static class FileConstant {
        public static final String PROVINCE_FILE_NAME = "province.txt";
        public static final String CITY_FILE_NAME = "city.txt";
        public static final String COUNTY_FILE_NAME = "county.txt";
    }

    /**
     * Created by zhengwuy on 2017/2/19.
     * Email: 963460692@qq.com
     * description: 存储sharedPreference的key
     */
    public static class SharedPreferenceKeyConstant {
        public static final String KEY_HAS_LOAD_DATA = "has_load_data";
        public static final String KEY_CHOOSE_COUNTY_WEATHER_ID = "choose_county_weather_id";
    }

    /**
     * Created by zhengwuy on 2017/1/31.
     * Email: 963460692@qq.com
     * description: http api的常量
     */

    public static class ApiConstant {
        //高德地图apiKey
        public static final String Amap_KEY = "1afff7faebdab35f9d8edf48a3542433";

        //天气相关的key
        public static final String URL = "http://guolin.tech/api/";
        public static final String WEATHER_KRY = "d74d81e2205841e98890a0a64980d14f";
        public static final String WEATHER_KEY_1 = "94cea876529f43a3baa0378496f6b418";
    }
}
