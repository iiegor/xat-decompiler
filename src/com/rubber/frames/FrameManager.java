/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
