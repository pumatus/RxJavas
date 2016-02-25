package com.rxjava.example.hehd.rxjavas.presenter;

import com.rxjava.example.hehd.rxjavas.bean.User;
import com.rxjava.example.hehd.rxjavas.model.UserModel;
import com.rxjava.example.hehd.rxjavas.view.UserView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hehd on 2016/2/24.
 * Presenter作为Model和View通讯的桥梁，需要持有它们的引用
 * Preserter相当于MVC当中的控制器，负责Modul和View的沟通
 */
public class UserPresenter {
    private UserView mUserView;

    private UserModel mUserModel;

    public UserPresenter(UserView mUserView){
        this.mUserView = mUserView;
        mUserModel = new UserModel();
    }

    public void getUser(){
        mUserView.showProgressDialog();

        //这里如果使用Lambda会更简洁
        mUserModel.getUser().subscribeOn(Schedulers.io())//在非线程中执行getUser
        .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行结果
        .subscribe(new Subscriber<User>() {

            @Override
            public void onCompleted() {
                mUserView.hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                mUserView.showError(e.getMessage());
                mUserView.hideProgressDialog();
            }

            @Override
            public void onNext(User user) {
                mUserView.updateView(user);
            }
        });
    }
}
