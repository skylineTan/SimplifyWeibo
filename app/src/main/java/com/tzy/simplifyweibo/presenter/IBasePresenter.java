package com.tzy.simplifyweibo.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by skylineTan on 2016/5/22 12:02.
 */
public abstract class IBasePresenter<E,T>{
    public Context mContext;
    public E mModel;
    public T mView;

    public void setVM(T v,E m){
        this.mView = v;
        this.mModel = m;
        if(v instanceof Activity){
            mContext = (Context) v;
        }else {
            mContext = ((Fragment)v).getActivity();
        }
        this.onStart();
    }

    public void onStart(){}
}
