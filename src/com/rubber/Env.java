package com.rubber;

/**
 * Created with Rubber, a simple manager for frames and services for java developed by Iegor.
 * 
 * @author Returns();
 * @twitter iiegor
 * @skype iegor3
 */
public final class Env {
    public static Manager Manager;

    public static void init() throws Exception {
        { // Anonymous block
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("______      _     _               \n"
                    + "| ___ \\    | |   | |              \n"
                    + "| |_/ /   _| |__ | |__   ___ _ __ \n"
                    + "|    / | | | '_ \\| '_ \\ / _ \\ '__|\n"
                    + "| |\\ \\ |_| | |_) | |_) |  __/ |   \n"
                    + "\\_| \\_\\__,_|_.__/|_.__/ \\___|_|   \n");
            System.out.println(sBuilder.toString());
        }
        if(Statics.DEBUG) Thread.sleep(1000); // Set timeout
        
        Manager = new Manager();
        Env.getManager().getFrameManager().getFrames().get("loader").invoke();
    }

    public static Manager getManager() {
        return Manager;
    }
}
