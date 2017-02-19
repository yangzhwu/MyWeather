package com.example.administrator.myweather;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.administrator.myweather.adapter.ProvinceAdapter;
import com.example.administrator.myweather.constant.SharedPreferenceKeyConstant;
import com.example.administrator.myweather.db.DBManager;
import com.example.administrator.myweather.db.ProvinceEntity;
import com.example.administrator.myweather.util.ActivityUtil;
import com.example.administrator.myweather.util.SharedPreferenceHelper;

import java.util.List;

public class ChooseProvinceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_province);

        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (SharedPreferenceHelper.getInstance().getBoolean(SharedPreferenceKeyConstant.KEY_HAS_LOAD_DATA, false)) {
            List<ProvinceEntity> list = DBManager.getInstance().queryProvinceList();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            ProvinceAdapter provinceAdapter = new ProvinceAdapter(list);
            recyclerView.setAdapter(provinceAdapter);
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
}
