package com.tzy.simplifyweibo.callback;

/**
 * Created by skylineTan on 2016/5/19 15:55.
 */
public interface StatusCallback {

    void onSuccess();

    void onFailure(String msg);

    void onError(String msg);
}
