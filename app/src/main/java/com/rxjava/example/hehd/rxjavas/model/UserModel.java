package com.rxjava.example.hehd.rxjavas.model;

import android.os.SystemClock;

import com.rxjava.example.hehd.rxjavas.bean.User;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by hehd on 2016/2/24.
 */
public class UserModel {
    public Observable<User> getUser(){

        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                //设置个2000ms的延迟，模拟网络访问、数据库操作等等延时操作
                SystemClock.sleep(2000);
                //final User USER = NULL;
                final User user = new User("赵日天");
                if(user == null){
                    subscriber.onError(new Exception("user = null"));
                }else{
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
