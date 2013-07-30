package com.server;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RouterTest {
    Router router;
    Hashtable<String, String> request= new Hashtable<String, String>();
    Hashtable<String, String> response;

    @Before public void init() {
        router = new Router();
    }

    @Test public void routeNotFound() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Request-URI", "/wrong_uri");
        response = router.route(request);
        assertEquals("HTTP/1.0", response.get("HTTP-Version"));
        assertEquals("404", response.get("Status-Code"));
        assertEquals("Not Found", response.get("Reason-Phrase"));
    }

    @Test public void addRoute() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Request-URI", "/");
        response = router.route(request);
        assertEquals("HTTP/1.0", response.get("HTTP-Version"));
        assertEquals("200", response.get("Status-Code"));
        assertEquals("OK", response.get("Reason-Phrase"));
    }

    @Test public void routeFormShouldBeTwoHundred() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Request-URI", "/form");
        response = router.route(request);
        assertEquals("HTTP/1.0", response.get("HTTP-Version"));
        assertEquals("200", response.get("Status-Code"));
        assertEquals("OK", response.get("Reason-Phrase"));
    }

    @Test public void routeDoesNotTakeData() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Request-URI", "/form");
        request.put("Method", "GET");
        request.put("data", "Cosby");
        response = router.route(request);
        assertEquals(" ", response.get("Body"));
    }
}
