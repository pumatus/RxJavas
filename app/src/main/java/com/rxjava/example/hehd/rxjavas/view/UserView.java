package com.rxjava.example.hehd.rxjavas.view;

import com.rxjava.example.hehd.rxjavas.bean.User;

/**
 * Created by hehd on 2016/2/24.
 */
public interface UserView {
    void updateView(User user);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}
