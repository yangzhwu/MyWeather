package com.example.administrator.myweather.db;

import android.content.Context;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by zhengwuy on 2017/2/18.
 * Email: 963460692@qq.com
 * description:
 */

public class DBManager {
    private static final String DB_NAME = "weather_db";
    private static DBManager mDBManagerInstance;
    private DaoSession mDaoSession;

    public static DBManager getInstance(Context context) {
        if (mDBManagerInstance == null) {
            synchronized (DBManager.class) {
                if (mDBManagerInstance == null) {
                    mDBManagerInstance = new DBManager(context.getApplicationContext());
                }
            }
        }
        return mDBManagerInstance;
    }

    private DBManager(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = daoMaster.newSession();
    }

    /**
     * 插入省份数据
     * @param provinceEntityList 数据
     */
    public void insertProvinceList(List<ProvinceEntity> provinceEntityList) {
        ProvinceEntityDao provinceEntityDao = mDaoSession.getProvinceEntityDao();
        provinceEntityDao.insertInTx(provinceEntityList);
    }

    public List<ProvinceEntity> queryProvinceList() {
        ProvinceEntityDao provinceEntityDao = mDaoSession.getProvinceEntityDao();
        return provinceEntityDao.loadAll();
    }

    public List<CityEntity> queryCityListByProvinceId(String provinceId) {
        CityEntityDao cityEntityDao = mDaoSession.getCityEntityDao();
        Query<CityEntity> query = cityEntityDao.queryBuilder().where(CityEntityDao.Properties.MProvinceId.eq(provinceId))
                .build();
        return query.list();
    }

    public List<CountyEntity> queryCountyListByCityId(String cityId) {
        CountyEntityDao countyEntityDao = mDaoSession.getCountyEntityDao();
        Query<CountyEntity> query = countyEntityDao.queryBuilder().where(CountyEntityDao.Properties.MCityId.eq(cityId)).build();
        return query.list();
    }

    public List<CountyEntity> queryCountyListByCountyName(String countyName) {
        CountyEntityDao countyEntityDao = mDaoSession.getCountyEntityDao();
        Query<CountyEntity> query = countyEntityDao.queryBuilder().where(CountyEntityDao.Properties.MCountyName.eq(countyName)).build();
        return query.list();
    }

    /**
     * 插入市数据
     * @param cityEntityList 数据
     */
    public void insertCityList(List<CityEntity> cityEntityList) {
        CityEntityDao cityEntityDao = mDaoSession.getCityEntityDao();
        cityEntityDao.insertInTx(cityEntityList);
    }

    /**
     * 插入县的数据
     * @param countyEntityList 数据
     */
    public void insertCountyList(List<CountyEntity> countyEntityList) {
        CountyEntityDao countyEntityDao = mDaoSession.getCountyEntityDao();
        countyEntityDao.insertInTx(countyEntityList);
    }
}
