package com.example.administrator.myweather.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myweather.activity.ChooseProvinceActivity;
import com.example.administrator.myweather.R;
import com.example.administrator.myweather.constant.BundleConstant;

/**
 * Created by zhengwuy on 2017/2/19.
 * Email: 963460692@qq.com
 * description:
 */

public class ActivityUtil {

    /**
     * 跳转到选择地区界面
     * @param context 上下文
     * @param chooseType 选择省，市或者县
     * @param id 查询市的数据需要传入省的id， 查询县的数据需要传入市的id, 查看省的数据不需要传入id
     */
    public static void goChooseProvinceActivity(Context context, int chooseType, String id) {
        Intent intent = new Intent(context, ChooseProvinceActivity.class);
        intent.putExtra(BundleConstant.CHOOSE_TYPE, chooseType);
        intent.putExtra(BundleConstant.CHOOSE_ID, id);
        context.startActivity(intent);
        startAnim((AppCompatActivity) context);
    }

    public static void finishAnim(AppCompatActivity context) {
        context.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    public static void startAnim(AppCompatActivity context) {
        context.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
