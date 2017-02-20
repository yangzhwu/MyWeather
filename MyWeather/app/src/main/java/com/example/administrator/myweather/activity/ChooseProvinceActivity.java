package com.example.administrator.myweather.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.adapter.CityAdapter;
import com.example.administrator.myweather.adapter.CountyAdapter;
import com.example.administrator.myweather.adapter.ProvinceAdapter;
import com.example.administrator.myweather.constant.BundleConstant;
import com.example.administrator.myweather.constant.SharedPreferenceKeyConstant;
import com.example.administrator.myweather.db.CityEntity;
import com.example.administrator.myweather.db.CountyEntity;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.db.ProvinceEntity;
import com.example.administrator.myweather.util.ActivityUtil;
import com.example.administrator.myweather.util.SharedPreferenceHelper;

import java.util.List;

public class ChooseProvinceActivity extends AppCompatActivity {
    public final static int CHOOSE_TYPE_PROVINCE = 1;
    public final static int CHOOSE_TYPE_CITY = 2;
    public final static int CHOOSE_TYPE_COUNTY = 3;

    private int mCurrentMode = CHOOSE_TYPE_PROVINCE;
    private String mId;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_province);

        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (SharedPreferenceHelper.getInstance().getBoolean(SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, false)) {
            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
        }
    }

    /**
     * 初始化 adapter
     */
    private void initData() {
        if (getIntent() != null) {
            mCurrentMode = getIntent().getIntExtra(BundleConstant.CHOOSE_TYPE, CHOOSE_TYPE_PROVINCE);
            if (mCurrentMode != CHOOSE_TYPE_PROVINCE) {
                mId = getIntent().getStringExtra(BundleConstant.CHOOSE_ID);
                if (mCurrentMode == CHOOSE_TYPE_CITY) {
                    List<CityEntity> list = DBManager.getInstance().queryCityListByProvinceId(mId);
                    CityAdapter cityAdapter = new CityAdapter(this, list);
                    mRecyclerView.setAdapter(cityAdapter);
                } else {
                    List<CountyEntity> list = DBManager.getInstance().queryCountyListByCityId(mId);
                    CountyAdapter countyAdapter = new CountyAdapter(this, list);
                    mRecyclerView.setAdapter(countyAdapter);
                }
            } else {
                List<ProvinceEntity> list = DBManager.getInstance().queryProvinceList();
                ProvinceAdapter provinceAdapter = new ProvinceAdapter(this, list);
                mRecyclerView.setAdapter(provinceAdapter);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                ActivityUtil.finishAnim(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        ActivityUtil.finishAnim(this);
    }
}
