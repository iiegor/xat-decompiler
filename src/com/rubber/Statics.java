/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rubber;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Iegor
 */
public final class Statics {
    public final static String properties_name = "config.properties";
    public final static String author = "Returns();";
    public static String domaincrack = "example.com";
    public static String portcrack = "1234";
    
    public static ArrayList<File> advancedCrackFilesArr = new ArrayList<>();
    
    public final static boolean debug = false;
    public static boolean xmlcrack = false;
    public static boolean autoupdate = false;
    public static boolean advancedCrack = false;
    
    public static java.io.File fileChoosed = null;
    
    public static final double Version = 0.4;
}
