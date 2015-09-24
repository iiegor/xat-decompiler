package com.rubber.frames;

import java.util.HashMap;

/**
 *
 * @author Iegor
 */
public class FrameManager {
    public HashMap<String, IFrameEvent> HashMap;
    
    public FrameManager() {
        this.HashMap = new HashMap();
        this.HashMap.put("loader", new Loader());
        this.HashMap.put("main", new Main());
    }
    
    public HashMap<String, IFrameEvent> getFrames() {
        return this.HashMap;
    }
}
