package com.rubber.utils;

import java.io.File;

/**
 *
 * @author Iegor
 */
public class Folder {

    public static void DeleteFileFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            do {
                delete(file);
            } while (file.exists());
        } else {
            System.out.println("File or Folder not found : " + path);
        }

    }

    public static void delete(File file) {
        if (file.isDirectory()) {
            String fileList[] = file.list();
            if (fileList.length == 0) {
                file.delete();
            } else {
                int size = fileList.length;
                for (int i = 0; i < size; i++) {
                    String fileName = fileList[i];
                    String fullPath = file.getPath() + "/" + fileName;
                    File fileOrFolder = new File(fullPath);
                    delete(fileOrFolder);
                }
            }
        } else {
            file.delete();
        }
    }
}
