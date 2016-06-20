package com.tzy.simplifyweibo.callback;

/**
 * Created by skylineTan on 2016/5/19 15:17.
 */
public abstract class DataCallback<T> {

    public abstract void onSuccess(T t);

    public void onFailure(String msg){}

    public void onError(String msg){}
}
