package com.example.administrator.myweather.db;

import android.content.Context;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * Created by zhengwuy on 2017/2/18.
 * Email: 963460692@qq.com
 * description:
 */

@Entity
public class CityEntity {
    @Id(autoincrement = true)
    private Long id;

    private String mCityId;

    private String mCityName;

    private String mProvinceId;

    @Generated(hash = 1634354092)
    public CityEntity(Long id, String mCityId, String mCityName,
            String mProvinceId) {
        this.id = id;
        this.mCityId = mCityId;
        this.mCityName = mCityName;
        this.mProvinceId = mProvinceId;
    }

    @Generated(hash = 2001321047)
    public CityEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getMCityId() {
        return this.mCityId;
    }

    public void setMCityId(String mCityId) {
        this.mCityId = mCityId;
    }

    public String getMCityName() {
        return this.mCityName;
    }

    public void setMCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getMProvinceId() {
        return this.mProvinceId;
    }

    public void setMProvinceId(String mProvinceId) {
        this.mProvinceId = mProvinceId;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
