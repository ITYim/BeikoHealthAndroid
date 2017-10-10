package com.yim.base.utils;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {
	  //线程池
    private static ExecutorService threadPool;
    public  static Handler handler=new Handler();
    static {
    	int cpuNums = Runtime.getRuntime().availableProcessors();
        threadPool = Executors.newFixedThreadPool(cpuNums*5);
    }
    /**
     * 从线程池中抽取线程，执行指定的Runnable对象
     * @param runnable
     */
    public static void execute(Runnable runnable){
    	threadPool.execute(runnable);
    }
    public static ExecutorService getThreadPool(){
		return threadPool;
    }
}
