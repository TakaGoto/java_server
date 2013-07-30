package com.server;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class FileReaderTest {
    String publicDir;

    @Before public void init() {
        publicDir = System.getProperty("user.dir") + "/public";
    }

    @Test public void fileDoesNotExit() throws IOException {
        String publicDir = System.getProperty("user.dir") + "/public";
        byte[] result = FileReader.read("NotAFile", publicDir);
        assertEquals(0, result.length);
    }

    @Test public void fileExists() throws IOException {
        File file = new File(publicDir + "/totoro.jpg");
        byte[] fileByte = new byte[(int) file.length()];
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
        for (int i = 0; i < fileByte.length; i++) {
            fileByte[i] = (byte) input.read();
        }
        input.close();

        assertTrue(Arrays.equals(fileByte, FileReader.read("/totoro.jpg", publicDir)));
    }
}
