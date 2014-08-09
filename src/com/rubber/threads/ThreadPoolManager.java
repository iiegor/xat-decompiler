/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
