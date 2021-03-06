package com.rubber.utils;

import com.rubber.Statics;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFrame;

/**
 *
 * @author Iegor
 */
final public class Configuration extends JFrame {

    public Properties Configuration;

    public Configuration(String file) {
        try {
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(file)) {
                props.load(fis);
                Configuration = props;
                fis.close();
            }
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Configuration file (".concat(file).concat(") not found."), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            ex.printStackTrace();
        }
    }
}
