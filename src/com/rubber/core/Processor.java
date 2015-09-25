package com.rubber.core;

import com.rubber.Statics;
import com.rubber.frames.Main;
import com.rubber.utils.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * @author Iegor, Nasty35
 */
final public class Processor extends Thread {

    public String message;
    private Process p;
    private BufferedReader stdInput;
    private final Main form;
    private final String[] injectionPaths = new String[]{
        "network.class.asasm",
        "main.class.asasm",
        "chat.class.asasm",
        "basicxavi.class.asasm",
        "todo.class.asasm",
        "xconst.class.asasm",
        "xatlib.class.asasm",
        "xkiss.class.asasm",
        "xmessage.class.asasm",
        "DialogHelp.class.asasm",
        "DialogEdit.class.asasm"
    }, needInjection = new String[]{
        "http://xat.com/wiki for detailed help.",
        "xat.com",
        "xatech.com"
    }, afterInjection = new String[]{
        "Decompiled with http://github.com/iiegor/xat-decompiler",
        Statics.domaincrack,
        Statics.domaincrack
    };
    private final static int maxAttempts = 2;
    private final static String partName = Statics.fileChoosed.getName().replace(".swf", "-");

    public Processor(Main form) {
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
            p = Runtime.getRuntime().exec(Statics.crypoFolder + "/abcexport ".concat(Statics.fileChoosed.getAbsolutePath()));
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream())); // Reader por input

            if (p.waitFor() == 0) {
                if (stage2() == 0) {
                    this.inject(injectionPaths, needInjection, afterInjection);
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
                String name = partName + attempts;
                p = Runtime.getRuntime().exec(Statics.crypoFolder + "/rabcdasm " + Statics.fileChoosed.getParent() + "\\".concat(name).concat(".abc"));
                attempts++;
            } while (attempts != maxAttempts);

            return p.waitFor();

        } catch (Exception ex) {
            ex.printStackTrace();
            return 1;
        }
    }

    public void inject(String[] paths, String[] oldStrings, String[] newStrings) {
        final String pathPrefix = Statics.fileChoosed.getParent() + "\\" + partName + 1 + "\\"; // Path
        ArrayList<String> lines = new ArrayList<String>();
        String line = null;

        try {
            for (String path : paths) {
                File f1 = new File(pathPrefix + path);
                FileReader fr = new FileReader(f1);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < oldStrings.length; i++) {
                        if (line.contains(oldStrings[i])) {
                            line = line.replace(oldStrings[i], newStrings[i]);
                        }
                    }
                    
                    /* Structure injection by Nasty35 */
                    if (path.equals(paths[0]) && line.contains("callproperty        QName(PackageInternalNs(\"\"), \"PickIP\"), 1")) {
                        lines.add(line);
 
                        for (int a = 0; a < 11; a++) lines.add(br.readLine());
                        
                        for(int a = 0; a < 5; a++) br.readLine();
                        
                        lines.add("     pushstring          \"" + Statics.portcrack + "\"");
                        lines.add("     setproperty         QName(PackageNamespace(\"\"), \"useport\")\n");
                        
                        br.readLine();
                        
                        continue;
                    } else if(path.equals(paths[0]) && line.contains("QName(PackageNamespace(\"flash.system\"), \"Security\")") && Statics.xmlcrack) {
                        for (int a = 0; a < 7; a++) br.readLine();
                        
                        continue;
                    }
                    
                    lines.add(line);
                }
                fr.close();
                br.close();

                FileWriter fw = new FileWriter(f1);
                BufferedWriter out = new BufferedWriter(fw);
                for (String s : lines) {
                    out.write(s + "\n");
                }
                out.flush();
                out.close();
                lines.clear();
                line = null;
            }

            this.advancedInjection(); // Next
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(form, "Failed when trying to crack the swf file.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            form.finishProcess();
            ex.printStackTrace();
        }
    }
    
    public void advancedInjection() {
        if(Statics.advancedCrack) {
            Statics.advancedCrackFilesArr.stream().forEach((filePath) -> {
                try
                {
                    Files.copy(Paths.get(filePath.toString()), Paths.get(Statics.fileChoosed.getParent() + "\\" + partName + 1 + "\\" + filePath.getName()), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    this.compile();
                } catch(IOException ex) {
                    javax.swing.JOptionPane.showMessageDialog(form, "Failed when trying to inject custom components.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    form.finishProcess();
                    ex.printStackTrace();
                }
            });
        } else this.compile();
    }

    public void compile() {
        try {
            for (int i = 0; i < maxAttempts; i++) {
                String part = Statics.fileChoosed.getParent() + "\\" + partName + i + "\\" + partName + i;

                p = Runtime.getRuntime().exec(Statics.crypoFolder + "/rabcasm " + part + ".main.asasm");

                if (p.waitFor() == 0) {
                    p = Runtime.getRuntime().exec(Statics.crypoFolder + "/abcreplace " + Statics.fileChoosed.getAbsolutePath() + " " + i + " " + part + ".main.abc");
                }
            }

            if (p.waitFor() == 0) {
                Logger.log(Logger.Level.Info, "The file was compiled successfully!");
                /* CLEAR FILES */
                for (int i = 0; i < maxAttempts; i++) {
                    File part = new File(Statics.fileChoosed.getParent().concat("\\").concat(partName + i).concat(".abc"));
                    if(!Statics.DEBUG) com.rubber.utils.Folder.DeleteFileFolder(Statics.fileChoosed.getParent().concat("\\").concat(partName + i));

                    if (part.exists()) {
                        part.delete();
                    }
                }

                /* SET FINISH STATUS */
                form.finishProcess();
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(form, "Failed when trying to compile the swf file.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                form.finishProcess();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
