package com.tzy.simplifyweibo.di.modules;

import com.tzy.simplifyweibo.di.scopes.PerModel;
import com.tzy.simplifyweibo.model.IBaseModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by skylineTan on 2016/5/24 00:01.
 */
@Module
public class BaseModelModule {

    private IBaseModel mIBaseModel;

    public BaseModelModule(IBaseModel iBaseModel){
        mIBaseModel = iBaseModel;
    }

    @Provides @PerModel
    public IBaseModel provideBaseModel(){
        return mIBaseModel;
    }

}
