package com.tzy.simplifyweibo.view;

/**
 * Created by skylineTan on 2016/5/19 14:36.
 */
public interface IBaseView {

    void showError(String msg);

    void showLoading(String msg);

    void hideLoading();

    void showException(String msg);

    void showNetError();
}
