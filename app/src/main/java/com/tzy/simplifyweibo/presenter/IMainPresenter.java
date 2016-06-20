package com.tzy.simplifyweibo.presenter;

import android.content.Intent;
import com.tzy.simplifyweibo.model.IMainModel;
import com.tzy.simplifyweibo.view.IMainView;

/**
 * Created by skylineTan on 2016/5/19 14:40.
 */
public abstract class IMainPresenter extends IBasePresenter<IMainModel,IMainView>{

    protected abstract void authorizeUser();

    protected abstract void loadUser();

    protected abstract void authorizeCallBack(int requestCode, int resultCode,
                           Intent data);

    protected abstract void loadStatusList(String access_token,int page);

    protected abstract void loadMoreStatusList(String access_token,int page);

}
