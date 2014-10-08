/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Configuration extends JFrame {

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
            javax.swing.JOptionPane.showMessageDialog(this, Statics.properties_name.concat(" not found."), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            ex.printStackTrace();
        }
    }
}
