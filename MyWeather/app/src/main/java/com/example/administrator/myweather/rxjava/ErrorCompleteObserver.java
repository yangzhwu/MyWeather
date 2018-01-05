package com.example.administrator.myweather.rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhengwuy on 2018/1/3.
 * email: 13802885114@139.com
 * des:
 */

public abstract class ErrorCompleteObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T t) {

    }
    @Override
    public abstract void onError(Throwable e);

    @Override
    public abstract void onComplete();
}
