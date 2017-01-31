package com.example.administrator.myweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myweather.gson.ProvinceBean;
import com.example.administrator.myweather.internet.RetrofitUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private Button mGetProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGetProvince = (Button) findViewById(R.id.get_province);
        mGetProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<List<ProvinceBean>> observable = RetrofitUtil.getHttpService().listProvince();
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<ProvinceBean>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<ProvinceBean> list) {
                                for (ProvinceBean provinceBean: list) {
                                        Log.e(TAG, provinceBean.getId() + provinceBean.getName());
                                    }
                                mGetProvince.setText(list.get(0).getName());

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                mGetProvince.setText("complete");

                            }
                        });
//                new Thread() {
//                    @Override
//                    public void run() {
//                        Call<List<ProvinceBean>> call = RetrofitUtil.getHttpService().listProvince();
//                        call.enqueue(new Callback<List<ProvinceBean>>() {
//                            @Override
//                            public void onResponse(Call<List<ProvinceBean>> call, Response<List<ProvinceBean>> response) {
//                                if (response.isSuccessful()) {
//                                    List<ProvinceBean> list = response.body();
//                                    for (ProvinceBean provinceBean: list) {
//                                        Log.e(TAG, provinceBean.getId() + provinceBean.getName());
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<List<ProvinceBean>> call, Throwable t) {
//
//                            }
//                        });
//
//                    }
//                }.start();
            }
        });
    }
}
