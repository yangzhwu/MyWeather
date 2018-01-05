package com.example.administrator.myweather.LocalCache;

import com.example.administrator.myweather.gson.NowDataBean;
import com.example.administrator.myweather.gson.SuggestionDataBean;
import com.example.administrator.myweather.gson.WeatherBean;

/**
 * Created by zhengwuy on 2018/1/3.
 * email: 13802885114@139.com
 * des: 缓存类
 */

public class DataCache {
    private static DataCache sDataCache = new DataCache();
    private NowDataBean mNowDataBean;
    private SuggestionDataBean mSuggestionDataBean;
    private WeatherBean mWeatherBean;
    private boolean mIsLoad;

    private DataCache() {

    }

    public void setLoad(boolean isLoad) {
        mIsLoad = isLoad;
    }

    public boolean getLoad() {
        return mIsLoad;
    }

    public static DataCache getInstance() {
        return sDataCache;
    }

    public void setNowDataBean(NowDataBean nowDataBean) {
        mNowDataBean = nowDataBean;
    }

    public NowDataBean getNowDataBean() {
        return mNowDataBean;
    }

    public void setSuggestionDataBean(SuggestionDataBean suggestionDataBean) {
        mSuggestionDataBean = suggestionDataBean;
    }

    public SuggestionDataBean getSuggestionDataBean() {
        return mSuggestionDataBean;
    }

    public void setWeatherBean(WeatherBean weatherBean) {
        mWeatherBean = weatherBean;
    }

    public WeatherBean getWeatherBean() {
        return mWeatherBean;
    }
}
