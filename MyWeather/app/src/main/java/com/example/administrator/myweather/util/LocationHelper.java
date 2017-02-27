package com.example.administrator.myweather.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.administrator.myweather.R;

/**
 * Created by zhengwuy on 2017/2/27.
 * Emali: 963460692@qq.com
 * description:
 */

public class LocationHelper {
    private Context mContext;
    private boolean isLocating;
    private static LocationHelper mInstance;

    private LocationHelper(Context context) {
        mContext = context;
    }

    public static void init(Context context) {
        if (mInstance == null) {
            synchronized (LocationHelper.class) {
                if (mInstance == null) {
                    mInstance = new LocationHelper(context.getApplicationContext());
                }
            }
        }
    }

    public static LocationHelper getInstance() {
        return mInstance;
    }

    public void startLocation() {
        if (isLocating) {
            Toast.makeText(mContext, R.string.locating, Toast.LENGTH_SHORT).show();
        } else {
            isLocating = true;
            final AMapLocationClient aMapLocationClient = new AMapLocationClient(mContext);
            AMapLocationClientOption option = new AMapLocationClientOption();
            option.setOnceLocation(true);
            option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            option.setLocationCacheEnable(false);
            aMapLocationClient.setLocationOption(option);

            aMapLocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    isLocating = false;
                    //定位成功
                    if (aMapLocation.getErrorCode() == 0) {
                        Log.e("City Code", aMapLocation.getCityCode() + " " +
                                aMapLocation.getDistrict() + " " +
                                aMapLocation.getAdCode());

                    } else {
                        Toast.makeText(mContext, "location failed", Toast.LENGTH_SHORT).show();
                    }
                    aMapLocationClient.stopLocation();
                    aMapLocationClient.onDestroy();
                }
            });

            aMapLocationClient.startLocation();

        }

    }


}
