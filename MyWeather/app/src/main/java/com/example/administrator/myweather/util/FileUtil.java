package com.example.administrator.myweather.util;

import com.example.administrator.myweather.WeatherApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhengwuy on 2018/1/2.
 * email: 13802885114@139.com
 * des: 文件存储类
 */

public class FileUtil {
    private static final String TAG = "FileUtil";
    private static WeatherApplication sApplication;

    public static void init(WeatherApplication weatherApplication) {
        sApplication = weatherApplication;
    }

    /**
     * 将对象存入文件，文件名为对象名
     * @param cls class
     * @param beanInfo 对象
     */
    public static void saveBeanInfo(Class<?> cls, Object beanInfo) {
        Observable.create((ObservableOnSubscribe<Boolean>) e -> {
            String fileName = cls.getSimpleName();
            File file = new File(sApplication.getFilesDir(), fileName);
            if (file.exists()) {
                if (!file.delete()) {
                    LogUtil.e(TAG, "删除旧文件失败");
                    e.onNext(false);
                    e.onComplete();
                    return;
                }
            }
            if (file.createNewFile()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(beanInfo);
                e.onNext(true);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).subscribe(aBoolean -> {
            if (aBoolean) {
                LogUtil.d(TAG, "文件成功写入");
            } else {
                LogUtil.e(TAG, "文件操作失败");
            }
        });
    }

//    /**
//     * 将对象从文件中读取出来，文件名为对象名，结果回调在ui线程中
//     * @param cls class
//     * @param observer 观察者
//     * @param <T> 泛型
//     */
//    public static <T> void readBeanInfo(Class<T> cls, Observer<T> observer) {
//        Observable.create((ObservableOnSubscribe<T>) e -> {
//            String fileName = cls.getSimpleName();
//            File file = new File(sApplication.getFilesDir(), fileName);
//            if (file.exists()) {
//                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
//                Object object = objectInputStream.readObject();
//                e.onNext((T) object);
//
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
//    }

    /**
     * 将对象从文件中读取出来，文件名为对象名，结果回调在ui线程中
     * @param cls class
     * @param <T> 泛型
     * @return Observable<T> 返回observable
     */
    public static <T> Observable<T> readBeanInfo(Class<T> cls) {
        return Observable.create((ObservableOnSubscribe<T>) e -> {
            LogUtil.d(TAG, cls.getSimpleName());
            String fileName = cls.getSimpleName();
            File file = new File(sApplication.getFilesDir(), fileName);
            if (file.exists()) {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                Object object = objectInputStream.readObject();
                e.onNext((T) object);
                e.onComplete();
            } else {
                e.onComplete();
            }
        });
    }
}
