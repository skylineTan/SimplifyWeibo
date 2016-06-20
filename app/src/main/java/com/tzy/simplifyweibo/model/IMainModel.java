package com.tzy.simplifyweibo.model;

import android.content.Context;
import android.content.Intent;
import com.sina.weibo.sdk.openapi.models.User;
import com.tzy.simplifyweibo.bean.Status;
import com.tzy.simplifyweibo.callback.DataCallback;
import com.tzy.simplifyweibo.callback.StatusCallback;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/19 14:46.
 */
public interface IMainModel extends IBaseModel{

    void init(Context context);

    void authorize(Context context, StatusCallback callback);

    void getUser(Context context, DataCallback<User> callback);

    void authorizeCallBack(Context context,int requestCode, int resultCode,
                           Intent data);

    void getStatusList(String access_token, int page, DataCallback<List<Status>> statusList);

    void getMoreStatusList(String access_token, int page, DataCallback<List<Status>> statusList);
}
