/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubber.services;

import com.rubber.Env;
import com.rubber.Statics;
import com.rubber.utils.Logger;

/**
 *
 * @author Iegor
 */
public final class UpdateService implements IServiceEvent {

    public static String taskName = "Checking for updates...";
    private final String className = this.getClass().getName();

    @Override
    public void invoke() {
        if(Boolean.parseBoolean(Env.getManager().getConfig("autoupdate"))) Statics.autoupdate = true;
        else {
            Logger.log(Logger.Level.Info, className.concat(" is disabled."));
            return; //Block
        }
        
        Logger.log(Logger.Level.Info, className.concat(" is checking for updates..."));
    }
}
