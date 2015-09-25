package com.rubber.utils;

import java.awt.Frame;
import javax.swing.JDialog;

/**
 *
 * @author Iegor
 */
final public class Prettify {
    public static void Windows(Frame form, String title, boolean isResizable) {
        form.setTitle(title);
        form.setLocationRelativeTo(null);
        form.setResizable(isResizable);
        form.setVisible(true);
    }
    
    public static void Dialog(JDialog dialog, String title, boolean isResizable) {
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(isResizable);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle(title);
    }
}
