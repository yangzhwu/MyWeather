package com.example.administrator.myweather.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.activity.ChooseAreaActivity;
import com.example.administrator.myweather.activity.MainActivity;

/**
 * Created by zhengwuy on 2017/2/19.
 * Email: 963460692@qq.com
 * description:
 */

public class ActivityUtil {

    /**
     * 跳转到选择地区界面
     * @param appCompatActivity 选择地区页面会返回结果
     */
    public static void goChooseAreaActivity(AppCompatActivity appCompatActivity, int requestCode) {
        Intent intent = new Intent(appCompatActivity, ChooseAreaActivity.class);
        appCompatActivity.startActivityForResult(intent, requestCode);
        startAnim(appCompatActivity);
    }

    public static void goHomeActivity(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, MainActivity.class);
        appCompatActivity.startActivity(intent);
        startAnim(appCompatActivity);
    }

    public static void finishAnim(AppCompatActivity context) {
        context.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    private static void startAnim(AppCompatActivity context) {
        context.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
