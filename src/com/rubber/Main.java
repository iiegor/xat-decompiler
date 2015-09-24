package com.rubber;

/**
 *
 * @author Iegor
 */
public final class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Env.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
