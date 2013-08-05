package com.server.Handlers;

import org.junit.Test;

import java.io.*;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class FileHandlerTest {
    FileHandler file = new FileHandler("/Users/takayuki/Coding/java/cob_spec/public");
    Hashtable<String, Object> req = new Hashtable<String, Object>();
    Hashtable<String, Object> resp = new Hashtable<String, Object>();

    @Test public void testWrongMethod() throws IOException {
        req.put("Request-URI", "/file1");
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Method", "PUT");
        resp = file.respond(req);
        Hashtable<String, String> header = (Hashtable<String, String>) resp.get("message-header");
        assertEquals("HTTP/1.0 405 Method Not Allowed", resp.get("status-line"));
        assertEquals("GET", header.get("Allow"));
    }

    @Test public void getOKForGetRequest() throws IOException {
        req.put("Request-URI", "/file1");
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Method", "GET");
        resp = file.respond(req);
        assertEquals("HTTP/1.0 206 Partial Content", resp.get("status-line"));
    }

    @Test public void testPartialContent() throws IOException {
        req.put("Request-URI", "/partial_content.txt");
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Method", "GET");
        resp = file.respond(req);
        File newFile = new File(file.getRootDir() + "/partial_content.txt");
        assertEquals("HTTP/1.0 206 Partial Content", resp.get("status-line"));
        assertEquals(getFile(newFile), resp.get("message-body"));
    }

    public String getFile(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
