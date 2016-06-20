package com.tzy.simplifyweibo.model.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.tzy.simplifyweibo.R;
import com.tzy.simplifyweibo.SkylineApplication;
import com.tzy.simplifyweibo.bean.Status;
import com.tzy.simplifyweibo.callback.DataCallback;
import com.tzy.simplifyweibo.callback.StatusCallback;
import com.tzy.simplifyweibo.support.ApiService;
import com.tzy.simplifyweibo.support.Constants;
import com.tzy.simplifyweibo.support.SchedulerTransform;
import com.tzy.simplifyweibo.di.components.DaggerBaseModelComponent;
import com.tzy.simplifyweibo.di.modules.BaseModelModule;
import com.tzy.simplifyweibo.model.IMainModel;
import com.tzy.simplifyweibo.ui.activity.MainActivity;
import com.tzy.simplifyweibo.utils.AccessTokenKeeper;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by skylineTan on 2016/5/19 14:46.
 */
public class MainModel implements IMainModel {

    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;
    private int currentPage = 1;
    @Inject ApiService mApiService;


    @Override public void init(Context context) {
        mAccessToken = AccessTokenKeeper.readAccessToken(context);
        AuthInfo authInfo = new AuthInfo(context, Constants.APP_KEY,
                Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler((MainActivity) context, authInfo);
        DaggerBaseModelComponent.builder()
                                .appComponent(
                                        ((SkylineApplication) ((MainActivity) context)
                                                .getApplication()).getAppComponent())
                                .baseModelModule(new BaseModelModule(this))
                                .build()
                                .inject(this);
    }


    @Override
    public void authorize(final Context context, final StatusCallback callback) {
        if (mAccessToken.isSessionValid()) {
            callback.onSuccess();
            return;
        }
        mSsoHandler.authorize(new WeiboAuthListener() {
            @Override public void onComplete(Bundle bundle) {
                //从Bundle中解析Token
                Oauth2AccessToken accessToken
                        = Oauth2AccessToken.parseAccessToken(bundle);
                if (accessToken.isSessionValid()) {
                    AccessTokenKeeper.writeAccessToken(context, accessToken);
                    callback.onSuccess();
                }
            }


            @Override public void onWeiboException(WeiboException e) {
                callback.onError(e.getMessage());
            }


            @Override public void onCancel() {
                callback.onFailure(context.getResources()
                                          .getString(
                                                  R.string.cancel_authorize));
            }
        });
    }


    @Override
    public void getUser(Context context, final DataCallback<User> callback) {
        if (mAccessToken == null) return;
        UsersAPI usersAPI = new UsersAPI(context, Constants.APP_KEY,
                mAccessToken);
        long uid = Long.parseLong(mAccessToken.getUid());
        usersAPI.show(uid, new RequestListener() {
            @Override public void onComplete(String response) {
                User user = null;
                if (!TextUtils.isEmpty(response)) {
                    user = new Gson().fromJson(response, User.class);
                }
                if (user != null) {
                    callback.onSuccess(user);
                }
                else {
                    callback.onFailure(response);
                }
            }


            @Override public void onWeiboException(WeiboException e) {
                callback.onError(e.getMessage());
            }
        });
    }


    @Override
    public void authorizeCallBack(Context context, int requestCode, int resultCode, Intent data) {
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }


    @Override
    public void getStatusList(String access_token, int page, final DataCallback<List<Status>> callback) {
        //获取第一页的数据
        mApiService.getStatusList(access_token, currentPage, 20)
                   .compose(new SchedulerTransform<>())
                   .subscribe(statusWrapper -> {
                       callback.onSuccess(statusWrapper.statuses);
                   }, throwable -> {
                       callback.onError(throwable.getMessage());
                   });
    }


    @Override
    public void getMoreStatusList(String access_token, int page, DataCallback<List<Status>> callback) {
        mApiService.getStatusList(access_token, currentPage++, 20)
                   .compose(new SchedulerTransform<>())
                   .subscribe(statusWrapper -> {
                       callback.onSuccess(statusWrapper.statuses);
                   }, throwable -> {
                       callback.onError(throwable.getMessage());
                   });
    }
}
