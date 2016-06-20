package com.tzy.simplifyweibo.di.modules;

import android.content.Context;
import com.tzy.simplifyweibo.support.ApiService;
import com.tzy.simplifyweibo.support.RetrofitManager;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

/**
 * Created by skylineTan on 2016/5/24 00:03.
 */
@Module public class AppModule {

    Context mContext;

    private static final int CPU_COUNT = Runtime.getRuntime()
                                                .availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;
    private static final BlockingQueue<Runnable> poolWorkQueue
            = new ArrayBlockingQueue<>(10);


    public AppModule(Context context) {
        mContext = context;
    }


    @Provides @Singleton public Context provideContext() {
        return mContext;
    }


    @Provides @Singleton ThreadPoolExecutor provideThreadExecutor() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE, TimeUnit.SECONDS, poolWorkQueue);
    }


    @Provides @Singleton ApiService provideApiService(){
        return RetrofitManager.getService();
    }
}
