package com.rubber.services;

import com.rubber.utils.Logger;
import java.io.File;
import javax.swing.JFrame;
import com.rubber.Statics;

/**
 *
 * @author Iegor
 */
public class CrypoService extends JFrame implements IServiceEvent {

    public boolean exists() {
        File file;
        if (Statics.DEBUG) {
            Statics.crypoFolder = "third_party/RABCDAsm";
            file = new File("third_party/RABCDAsm");
        } else file = new File(Statics.crypoFolder);
        
        Logger.log(Logger.Level.Debug, "Searching for ".concat(Statics.crypoFolder).concat("folder..."));
     
        return file.exists();
    }

    @Override
    public void invoke() {
        if (exists()) {
            Logger.log(Logger.Level.Info, "CrypoService started!");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Crypo folder (".concat(Statics.crypoFolder).concat(") not found."), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            Logger.log(Logger.Level.Error, "Crypo folder (".concat(Statics.crypoFolder).concat(") not found."));
            System.exit(0);
        }
    }
    
}
