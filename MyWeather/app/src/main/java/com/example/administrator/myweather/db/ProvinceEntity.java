package com.example.administrator.myweather.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhengwuy on 2017/2/18.
 * Email: 963460692@qq.com
 * description:
 */

@Entity
public class ProvinceEntity {

    @Id(autoincrement = true)
    private Long id;

    private String mProvinceId;

    private String mProvinceName;

    @Generated(hash = 1924644945)
    public ProvinceEntity(Long id, String mProvinceId, String mProvinceName) {
        this.id = id;
        this.mProvinceId = mProvinceId;
        this.mProvinceName = mProvinceName;
    }

    @Generated(hash = 1419486807)
    public ProvinceEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getMProvinceId() {
        return this.mProvinceId;
    }

    public void setMProvinceId(String mProvinceId) {
        this.mProvinceId = mProvinceId;
    }

    public String getMProvinceName() {
        return this.mProvinceName;
    }

    public void setMProvinceName(String mProvinceName) {
        this.mProvinceName = mProvinceName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
