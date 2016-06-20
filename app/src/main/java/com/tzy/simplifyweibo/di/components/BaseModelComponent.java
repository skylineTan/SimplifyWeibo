package com.tzy.simplifyweibo.di.components;

import com.tzy.simplifyweibo.di.modules.BaseModelModule;
import com.tzy.simplifyweibo.di.scopes.PerModel;
import com.tzy.simplifyweibo.model.IBaseModel;
import com.tzy.simplifyweibo.model.impl.MainModel;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by skylineTan on 2016/5/24 09:54.
 */
@PerModel
@Component(dependencies = AppComponent.class,modules = BaseModelModule.class)
public interface BaseModelComponent {
    IBaseModel basemodel();

    void inject(MainModel mainModel);

}
