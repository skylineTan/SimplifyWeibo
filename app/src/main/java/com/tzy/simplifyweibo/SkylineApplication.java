package com.tzy.simplifyweibo;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;
import com.tzy.simplifyweibo.di.components.AppComponent;
import com.tzy.simplifyweibo.di.components.DaggerAppComponent;
import com.tzy.simplifyweibo.di.modules.AppModule;

/**
 * Created by skylineTan on 2016/5/20 20:54.
 */
public class SkylineApplication extends Application {

    AppComponent mAppComponent;
    private static SkylineApplication context;


    @Override public void onCreate() {
        super.onCreate();
        context = this;

        Logger.init("SimplifyWeibo");

        mAppComponent = DaggerAppComponent.builder()
                                          .appModule(new AppModule(this))
                                          .build();
        Stetho.initializeWithDefaults(this);
    }

    public static SkylineApplication getContext() {
        return context;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
