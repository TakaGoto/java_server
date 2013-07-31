package com.server;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RouterTest {
    Router router;
    Hashtable<String, String> request= new Hashtable<String, String>();
    Hashtable<String, Object> response;
    Hashtable<String, String> statusLine;
    Hashtable<String, String> header;

    @Before public void init() {
        router = new Router();
    }

    @Test public void routeNotFound() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Request-URI", "/wrong_uri");
        request.put("Body", "Tonorino Totoro");
        response = router.route(request);
        assertEquals("HTTP/1.0 404 Not Found\r\n\r\n", response.get("status-line"));
    }

    @Test public void addRoute() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Request-URI", "/");
        request.put("Body", "Tonorino Totoro");
        response = router.route(request);
        assertEquals("HTTP/1.0 200 OK\r\n\r\n", response.get("status-line"));
    }

    @Test public void routeFormShouldBeTwoHundred() {
        request.put("HTTP-Version", "HTTP/1.0");
        request.put("Request-URI", "/form");
        request.put("Body", "Tonorino Totoro");
        response = router.route(request);
        assertEquals("HTTP/1.0 200 OK\r\n\r\n", response.get("status-line"));
    }

//    @Test public void routeRedirectIsTwoHundred() {
//        request.put("HTTP-Version", "HTTP/1.0");
//        request.put("Request-URI", "/redirect");
//        request.put("Body", "Tonarino Totoro");
//        response = router.route(request);
//        assertEquals("HTTP/1.0 200 OK", response.get("status-line"));
//    }

//    @Test public void routeRedirectsToRoot() {
//        request.put("HTTP-Version", "HTTP/1.0");
//        request.put("Request-URI", "/redirect");
//        request.put("Body", "Tonarino Totoro");
//        response = router.route(request);
//        header = (Hashtable<String, String>) response.get("message-header");
//        assertEquals("HTTP/1.0 301 Moved Permanently", response.get("status-line"));
//        assertEquals("http://localhost:5000/", header.get("Location"));
//    }
//
//    @Test public void respRedirect() {
//        request.put("HTTP-Version", "HTTP/1.1");
//        request.put("Host", "http://localhost:5000/redirect");
//        response = router.respRedirect(request);
//        statusLine = (Hashtable<String, String>) response.get("status-line");
//        assertEquals("HTTP/1.0 301 Moved Permanently\n", response.get("status-line"));
//        assertEquals("http://localhost:5000/", header.get("Location"));
//    }
}
