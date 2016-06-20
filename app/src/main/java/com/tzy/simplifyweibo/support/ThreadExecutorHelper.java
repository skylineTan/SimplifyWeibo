package com.tzy.simplifyweibo.support;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by skylineTan on 2016/5/24 08:37.
 */
public class ThreadExecutorHelper {

    private static final int CPU_COUNT = Runtime.getRuntime()
                                                .availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;
    public static ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    private static final ThreadFactory threadFactory = new ThreadFactory() {
        @Override public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    };

    private static final BlockingQueue<Runnable> poolWorkQueue = new
            ArrayBlockingQueue<>(128);

    public static ThreadPoolExecutor getThreadPoolExecutor(){
        if(THREAD_POOL_EXECUTOR == null){
            synchronized(ThreadExecutorHelper.class){
                if(THREAD_POOL_EXECUTOR == null){
                    return THREAD_POOL_EXECUTOR = new
                            ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEP_ALIVE,
                            TimeUnit.SECONDS,poolWorkQueue);
                }
            }
        }
        return THREAD_POOL_EXECUTOR;
    }
}
