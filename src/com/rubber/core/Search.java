/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubber.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Iegor
 */
public class Search {

    public static ArrayList<String> lines = new ArrayList<String>();
    public static String line = null;

    public static boolean replace(String path, String oldString, String newString) {
        try {
            File f1 = new File(path);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(oldString)) {
                    line = line.replace(oldString, newString);
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
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
