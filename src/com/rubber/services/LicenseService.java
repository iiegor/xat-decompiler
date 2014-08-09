/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rubber.services;

import com.rubber.Env;
import com.rubber.utils.Logger;

/**
 *
 * @author Iegor
 */
public class LicenseService implements IServiceEvent {
    private static String keyStr = "beta"; // TEST...
    
    @Override
    public void invoke() {
        Logger.log(Logger.Level.Debug, "Validating the license: ".concat(Env.getManager().getConfig("license")));
        
        if(keyStr.equals(Env.getManager().getConfig("license"))) Logger.log(Logger.Level.Info, "The license is correct!");
    }
}
