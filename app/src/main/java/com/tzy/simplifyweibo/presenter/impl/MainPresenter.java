package com.tzy.simplifyweibo.presenter.impl;

import android.content.Intent;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.openapi.models.User;
import com.tzy.simplifyweibo.bean.Status;
import com.tzy.simplifyweibo.callback.DataCallback;
import com.tzy.simplifyweibo.callback.StatusCallback;
import com.tzy.simplifyweibo.presenter.IMainPresenter;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/19 14:45.
 */
public class MainPresenter extends IMainPresenter {

    @Override public void onStart() {
        super.onStart();
        mModel.init(mContext);
    }


    @Override public void authorizeUser() {
        mModel.authorize(mContext, new StatusCallback() {
            @Override public void onSuccess() {
                loadUser();
            }


            @Override public void onFailure(String msg) {
                mView.showError(msg);
            }


            @Override public void onError(String msg) {
                mView.showError(msg);
            }
        });
    }


    @Override public void loadUser() {
        mModel.getUser(mContext, new DataCallback<User>() {
            @Override public void onSuccess(User user) {
                mView.showUser(user);
            }


            @Override public void onFailure(String msg) {
                mView.showError(msg);
            }


            @Override public void onError(String msg) {
                mView.showError(msg);
            }
        });
    }


    @Override
    public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
        mModel.authorizeCallBack(mContext, requestCode, resultCode, data);
    }


    @Override public void loadStatusList(String access_token, int page) {
        mView.showProgress();
        mModel.getStatusList(access_token, page, new DataCallback<List<Status>>() {
            @Override public void onSuccess(List<Status> statuses) {
                mView.dismissProgress();
                mView.showStatusList(statuses);
            }

            @Override public void onError(String msg) {
                mView.dismissProgress();
                mView.showError(msg);
            }
        });
    }


    @Override public void loadMoreStatusList(String access_token, int page) {
        mModel.getMoreStatusList(access_token, 1, new DataCallback<List<Status>>() {
            @Override public void onSuccess(List<Status> statusList) {
                mView.showMoreStatusList(statusList);
            }


            @Override public void onError(String msg) {
                super.onError(msg);
                mView.showError(msg);
            }
        });
    }
}
