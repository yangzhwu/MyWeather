package com.example.administrator.myweather.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myweather.ChooseProvinceActivity;
import com.example.administrator.myweather.R;

/**
 * Created by zhengwuy on 2017/2/19.
 * Email: 963460692@qq.com
 * description:
 */

public class ActivityUtil {

    public static void goChooseProvinceActivity(Context context) {
        Intent intent = new Intent(context, ChooseProvinceActivity.class);
        context.startActivity(intent);
    }

    public static void finishAnim(AppCompatActivity context) {
        context.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    public static void startAnim(AppCompatActivity context) {
        context.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
