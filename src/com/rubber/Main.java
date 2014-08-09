/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
