package com.rubber;

import com.rubber.frames.FrameManager;
import com.rubber.services.QueueService;
import com.rubber.threads.ThreadPoolManager;
import com.rubber.utils.Configuration;

/**
 *
 * @author Iegor
 */
public class Manager {
    public FrameManager FrameManager;
    public Configuration Configuration;
    public ThreadPoolManager ThreadPoolManager;
    
    public Manager() throws Exception {
        this.Configuration = new Configuration("config.properties");
        this.FrameManager = new FrameManager();
        this.ThreadPoolManager = new ThreadPoolManager();
    }
    
    public FrameManager getFrameManager() {
        return this.FrameManager;
    }
    
    public String getConfig(String key) {
        return this.Configuration.Configuration.getProperty(key);
    }
    
    public ThreadPoolManager getThreads() {
        return this.ThreadPoolManager;
    }
}
