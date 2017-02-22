package com.example.administrator.myweather.util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myweather.R;
import com.example.administrator.myweather.fragment.ChooseAreaFragment;

/**
 * Created by zhengwuy on 2017/2/22.
 * Emali: 963460692@qq.com
 * description:
 */

public class FragmentUtil {

    public static void replaceAreaFragment(AppCompatActivity appCompatActivity, int container_id,
                                           ChooseAreaFragment chooseAreaFragment, boolean backStack) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left,
                R.anim.in_from_left, R.anim.out_to_right);
        fragmentTransaction.replace(container_id, chooseAreaFragment);
        if (backStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
