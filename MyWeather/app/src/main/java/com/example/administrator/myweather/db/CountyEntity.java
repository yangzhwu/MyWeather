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
public class CountyEntity {
    @Id(autoincrement = true)
    private Long id;

    private String mCountyId;

    private String mCountyName;

    private String mWeatherId;

    private String mCityId;

    @Generated(hash = 581537999)
    public CountyEntity(Long id, String mCountyId, String mCountyName,
            String mWeatherId, String mCityId) {
        this.id = id;
        this.mCountyId = mCountyId;
        this.mCountyName = mCountyName;
        this.mWeatherId = mWeatherId;
        this.mCityId = mCityId;
    }

    @Generated(hash = 1596628137)
    public CountyEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getMCountyId() {
        return this.mCountyId;
    }

    public void setMCountyId(String mCountyId) {
        this.mCountyId = mCountyId;
    }

    public String getMCountyName() {
        return this.mCountyName;
    }

    public void setMCountyName(String mCountyName) {
        this.mCountyName = mCountyName;
    }

    public String getMWeatherId() {
        return this.mWeatherId;
    }

    public void setMWeatherId(String mWeatherId) {
        this.mWeatherId = mWeatherId;
    }

    public String getMCityId() {
        return this.mCityId;
    }

    public void setMCityId(String mCityId) {
        this.mCityId = mCityId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
