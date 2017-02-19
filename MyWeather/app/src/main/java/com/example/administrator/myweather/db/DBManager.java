package com.example.administrator.myweather.db;

import android.content.Context;

import java.util.List;

/**
 * Created by zhengwuy on 2017/2/18.
 * Email: 963460692@qq.com
 * description:
 */

public class DBManager {
    private static final String DB_NAME = "weather_db";
    private static DBManager mDBManagerInstance;
    private static Context mContext;
    private DaoMaster.DevOpenHelper mDevOpenHelper;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static DBManager getInstance() {
        if (mDBManagerInstance == null) {
            synchronized (DBManager.class) {
                if (mDBManagerInstance == null) {
                    mDBManagerInstance = new DBManager();
                }
            }
        }
        return mDBManagerInstance;
    }

    private DBManager() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
    }

    /**
     * 插入省份数据
     * @param provinceEntityList 数据
     */
    public void insertProvinceList(List<ProvinceEntity> provinceEntityList) {
        DaoMaster daoMaster = new DaoMaster(mDevOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ProvinceEntityDao provinceEntityDao = daoSession.getProvinceEntityDao();
        provinceEntityDao.insertInTx(provinceEntityList);
    }

    public List<ProvinceEntity> queryProvinceList() {
        DaoMaster daoMaster = new DaoMaster(mDevOpenHelper.getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ProvinceEntityDao provinceEntityDao = daoSession.getProvinceEntityDao();
        return provinceEntityDao.loadAll();
    }

    /**
     * 插入市数据
     * @param cityEntityList 数据
     */
    public void insertCityList(List<CityEntity> cityEntityList) {
        DaoMaster daoMaster = new DaoMaster(mDevOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CityEntityDao cityEntityDao = daoSession.getCityEntityDao();
        cityEntityDao.insertInTx(cityEntityList);
    }

    /**
     * 插入县的数据
     * @param countyEntityList 数据
     */
    public void insertCountyList(List<CountyEntity> countyEntityList) {
        DaoMaster daoMaster = new DaoMaster(mDevOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CountyEntityDao countyEntityDao = daoSession.getCountyEntityDao();
        countyEntityDao.insertInTx(countyEntityList);
    }
}
