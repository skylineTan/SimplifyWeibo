package com.tzy.simplifyweibo.di.components;

import android.content.Context;
import com.tzy.simplifyweibo.support.ApiService;
import com.tzy.simplifyweibo.di.modules.AppModule;
import dagger.Component;
import java.util.concurrent.ThreadPoolExecutor;
import javax.inject.Singleton;

/**
 * Created by skylineTan on 2016/5/24 09:50.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();

    ThreadPoolExecutor threadExecutor();

    ApiService apiService();

}
