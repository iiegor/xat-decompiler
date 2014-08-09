/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubber.core;

import com.rubber.Statics;
import com.rubber.frames.Main;
import com.rubber.services.CrypoService;
import com.rubber.utils.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import javax.swing.JFrame;

/**
 * Credits to RABCDASM
 *
 * @author Iegor
 */
public class Processor extends Thread {

    public String message;
    private Process p;
    private BufferedReader stdInput;
    private final Main form;
    
    public Processor(Main form){
        this.form = form;
    }
    /**
     * Stages descriptions
     *
     * @stage1 export the swf to abc files
     * @stage2 decompile the abc file into asm files
     * @stage3 search for string and edit
     * @stage4 compile all
     */
    @Override
    public void run() {
        try {
            p = Runtime.getRuntime().exec(CrypoService.crypoFolder + "/abcexport ".concat(Statics.fileChoosed.getAbsolutePath()));
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream())); // Reader por input

            if (p.waitFor() == 0) {
                if (stage2() == 0) {
                    stage3();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int stage2() {
        try {
            int attempts = 0;
            do {
                String name = Statics.fileChoosed.getName().replace(".swf", "-") + attempts;
                p = Runtime.getRuntime().exec(CrypoService.crypoFolder + "/rabcdasm " + Statics.fileChoosed.getParent() + "\\".concat(name).concat(".abc"));
                attempts++;
            } while (attempts != 2);

            return p.waitFor();

        } catch (Exception ex) {
            ex.printStackTrace();
            return 1;
        }
    }

    private void stage3() {
        String path = Statics.fileChoosed.getParent() + "\\" + Statics.fileChoosed.getName().replace(".swf", "-") + 1 + "\\"; // Path

        try {
            if(Search.replace(path + "DialogHelp.class.asasm", "http://xat.com/wiki for detailed help.", "Cracked by Returns();")) {
                Thread.sleep(5000);
                compile();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void compile() {
        String partName = Statics.fileChoosed.getName().replace(".swf", "-");
        String part0 = Statics.fileChoosed.getParent() + "\\" + partName + 0 + "\\" + partName + 0;
        String part1 = Statics.fileChoosed.getParent() + "\\" + partName + 1 + "\\" + partName + 1;
        
        try
        {
            p = Runtime.getRuntime().exec(CrypoService.crypoFolder + "/rabcasm " + part0 + ".main.asasm");
            p = Runtime.getRuntime().exec(CrypoService.crypoFolder + "/rabcasm " + part1 + ".main.asasm");
            
            if(p.waitFor() == 0) {
                p = Runtime.getRuntime().exec(CrypoService.crypoFolder + "/abcreplace " + Statics.fileChoosed.getAbsolutePath() + " 0 " + part0 + ".main.abc");
                p = Runtime.getRuntime().exec(CrypoService.crypoFolder + "/abcreplace " + Statics.fileChoosed.getAbsolutePath() + " 1 " + part1 + ".main.abc");
                
                if(p.waitFor() == 0) {
                    Logger.log(Logger.Level.Info, "The file was compiled successfully!");
                    form.finishProcess();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
