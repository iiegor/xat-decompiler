/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubber.services;

import com.rubber.utils.Logger;
import java.io.File;
import javax.swing.JFrame;

/**
 *
 * @author Iegor
 */
public class CrypoService extends JFrame implements IServiceEvent {

    public final static String crypoFolder = "rabcdasm";

    public boolean exists() {
        Logger.log(Logger.Level.Debug, "Searching for ".concat(crypoFolder).concat("folder..."));

        File f = new File(crypoFolder);
        return f.exists();
    }

    @Override
    public void invoke() {
        if (exists()) {
            Logger.log(Logger.Level.Info, "CrypoService started!");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Crypo folder (".concat(crypoFolder).concat(") not found."), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            Logger.log(Logger.Level.Error, "Crypo folder (".concat(crypoFolder).concat(") not found."));
            System.exit(0);
        }
    }
}
