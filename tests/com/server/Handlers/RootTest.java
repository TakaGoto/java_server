package com.server.Handlers;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RootTest {
    Root root;
    Hashtable<String, Object> req;

    @Before public void init() {
        root = new Root();
        req = new Hashtable<String, Object>();
    }

    @Test public void testRoot() {
        req.put("Request-URI", "/");
        req.put("Method", "GET");
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Host", "http://localhost:5000");
        Hashtable<String, Object> response = root.respond(req);
        assertEquals("HTTP/1.0 200 OK", response.get("status-line"));
    }

    @Test public void FourOhFourForAnyOtherMethods() {
        req.put("Request-URI", "/");
        req.put("Method", "PUT");
        req.put("HTTP-Version", "HTTP/1.0");
        req.put("Host", "http://localhost:5000");
        Hashtable<String, Object> response = root.respond(req);
        assertEquals("HTTP/1.0 404 Not Found", response.get("status-line"));
    }
}
