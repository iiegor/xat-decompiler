package com.rubber.utils;

/**
 *
 * @author Iegor
 */
final public class Logger {
    public static enum Level {
        Info,
        Debug,
        Error
    }
    
    public static void log(Logger.Level level, String str) {
        String strType;
        
        switch(level)
        {
            case Info:
                strType = "Info";
                break;
            case Debug:
                strType = "Debug";
                break;
            case Error:
                strType = "Error";
                break;
            default:
                strType = "?";
        }
        
        System.out.println(String.format("[%s] %s", strType, str));
    }
}
