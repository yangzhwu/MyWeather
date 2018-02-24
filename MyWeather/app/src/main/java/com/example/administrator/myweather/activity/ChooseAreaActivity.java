package com.example.administrator.myweather.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.fragment.ChooseAreaFragment;
import com.example.administrator.myweather.util.ActivityUtil;
import com.example.administrator.myweather.util.FragmentUtil;

public class ChooseAreaActivity extends AppCompatActivity {
    public final static int CHOOSE_TYPE_PROVINCE = 1;
    public final static int CHOOSE_TYPE_CITY = 2;
    public final static int CHOOSE_TYPE_COUNTY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_province);

        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ChooseAreaFragment chooseAreaFragment = ChooseAreaFragment.newInstance(CHOOSE_TYPE_PROVINCE, "");
        FragmentUtil.replaceAreaFragment(this, R.id.container, chooseAreaFragment, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
            ActivityUtil.finishAnim(this);
        } else {
            super.onBackPressed();
        }
    }
}
