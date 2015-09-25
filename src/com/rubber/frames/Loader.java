package com.rubber.frames;

import com.rubber.Env;
import com.rubber.Statics;
import com.rubber.services.QueueService;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 *
 * @author Iegor
 */
public class Loader implements IFrameEvent {

    @Override
    public void invoke() {
        /* Set windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        JFrame f = new JFrame();
        javax.swing.JLabel logo = new javax.swing.JLabel();
        f.setSize(450, 180);
        f.getContentPane().setBackground(Color.decode("#2D2D30"));
        f.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rubber/resources/logo.png"))); // NOI18N
        f.setUndecorated(true);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(logo))
                        .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(logo)
                        .addContainerGap(52, Short.MAX_VALUE))
        );
        com.rubber.utils.Prettify.Windows(f, "Cargando dependencias...", false);

        // If debug, wait and then execute
        try {
            if (Statics.DEBUG) {

                Thread.sleep(1000);

            } else {
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Execute the services in a thread
        Env.getManager().getThreads().executeOthers(new QueueService(f));
    }
}
