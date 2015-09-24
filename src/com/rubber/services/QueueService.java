package com.rubber.services;

import com.rubber.Env;
import java.awt.Frame;
import java.util.HashMap;

/**
 *
 * @author Iegor
 */
public class QueueService extends Thread {

    public HashMap<Integer, IServiceEvent> HashMap;
    public Frame loaderFrame;

    public QueueService(Frame loaderFrame) {
        this.loaderFrame = loaderFrame;
        this.HashMap = new HashMap<>();

        this.HashMap.put(1, new UpdateService());
        this.HashMap.put(2, new CrypoService());
        this.HashMap.put(3, new EnvironmentService());
    }

    public HashMap<Integer, IServiceEvent> getQueue() {
        return this.HashMap;
    }

    @Override
    public void run() {
        this.getQueue().keySet().stream().forEach((key) -> {
            this.getQueue().get(key).invoke();
        });

        try {
            Env.getManager().getFrameManager().getFrames().get("main").invoke();
            loaderFrame.setVisible(false);
            loaderFrame.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
