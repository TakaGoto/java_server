package com.server.Handlers;

import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class FileTest {
    File file = new File();
    Hashtable<String, Object> req = new Hashtable<String, Object>();
    Hashtable<String, Object> resp = new Hashtable<String, Object>();

    @Test public void testWrongMethod() {
        req.put("Request-URI", "/file1");
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Method", "PUT");
        resp = file.respond(req);
        Hashtable<String, String> header = (Hashtable<String, String>) resp.get("message-header");
        assertEquals("HTTP/1.0 405 Method Not Allowed", resp.get("status-line"));
        assertEquals("GET", header.get("Allow"));
    }

    @Test public void getOKForGetRequest() {
        req.put("Request-URI", "/file1");
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Method", "GET");
        resp = file.respond(req);
        Hashtable<String, String> header = (Hashtable<String, String>) resp.get("message-header");
        assertEquals("HTTP/1.0 200 OK", resp.get("status-line"));
    }
}
