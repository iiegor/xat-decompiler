/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rubber.services;

import com.sun.javafx.Utils;
import javax.swing.JFrame;

/**
 *
 * @author Iegor
 */
public class EnvironmentService extends JFrame implements IServiceEvent {
    
    @Override
    public void invoke() {
        if(Utils.isUnix() || Utils.isMac()) javax.swing.JOptionPane.showMessageDialog(this, "The linux and mac versions of this program are in a experimental phase.\nIf you continue with the execution of this program will be your responsability.", "Notice", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
