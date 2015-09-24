package com.rubber.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {

    private ExecutorService OthersThreadPool;

    public ThreadPoolManager() {
        this.OthersThreadPool = Executors.newCachedThreadPool();
    }

    public void executeOthers(Thread Runnable) {
        this.OthersThreadPool.execute(Runnable);
    }

    public void disposeThreadPools() {
        this.OthersThreadPool.shutdown();
        this.OthersThreadPool = null;
    }
}
