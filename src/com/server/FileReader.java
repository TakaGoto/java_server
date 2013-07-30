package com.server;

import java.io.*;

public class FileReader {
    public static byte[] read(String name, String directory) throws IOException {
        File file = new File(directory + name);
        if(!file.isFile()) return new byte[0];

        byte[] newFile = new byte[(int) file.length()];
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
        input.read(newFile);
        input.close();

        return newFile;
    }
}
