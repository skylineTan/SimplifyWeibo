package com.tzy.simplifyweibo.view;

import com.sina.weibo.sdk.openapi.models.User;
import com.tzy.simplifyweibo.bean.Status;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/19 14:44.
 */
public interface IMainView extends IBaseView{

    void showUser(User user);

    void showStatusList(List<Status> statusList);

    void showMoreStatusList(List<Status> statusList);

    void showProgress();

    void dismissProgress();
}
