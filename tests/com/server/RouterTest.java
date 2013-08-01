package com.server;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class RouterTest {
    Router router;
    Hashtable<String, Object> request= new Hashtable<String, Object>();
    Hashtable<String, String> body = new Hashtable<String, String>();
    Hashtable<String, Object> response;
    Hashtable<String, String> header;

    @Before public void init() {
        router = new Router();
        request.put("HTTP-Version", "HTTP/1.0");
        body.put("data", "cosby");
        body.put("length", "5");
        request.put("Body", body);
    }

    @Test public void routeNotFound() {
        request.put("Request-URI", "/wrong_uri");
        request.put("Method", "GET");
        response = router.route(request);
        assertEquals("HTTP/1.0 404 Not Found", response.get("status-line"));
    }

    @Test public void routeComesWithDefaultHTML() {
        assertEquals("<html><head><title></title></head><body> Empty </body></html>", router.getBody("/"));
    }

    @Test public void routeHTMLChange() {
        router.addBodyToRoute("Totoro", "/");
        assertEquals("<html><head><title></title></head><body> Totoro </body></html>", router.getBody("/"));
    }

    @Test public void addRoute() {
        request.put("Request-URI", "/");
        request.put("Method", "GET");
        response = router.route(request);
        assertEquals("HTTP/1.0 200 OK", response.get("status-line"));
    }

    @Test public void routeFormShouldBeTwoHundred() {
        request.put("Request-URI", "/form");
        request.put("Method", "GET");
        response = router.route(request);
        assertEquals("HTTP/1.0 200 OK", response.get("status-line"));
    }

    @Test public void routeRedirectsToRoot() {
        request.put("Request-URI", "/redirect");
        request.put("Method", "GET");
        response = router.route(request);
        header = (Hashtable<String, String>) response.get("message-header");
        assertEquals("HTTP/1.0 301 Moved Permanently", response.get("status-line"));
        assertEquals("http://localhost:5000/", header.get("Location"));
    }

    @Test public void postDataToBody() {
        request.put("Request-URI", "/redirect");
        request.put("Method", "POST");
        response = router.route(request);
        assertEquals("<html><head><title></title></head><body> data = cosby </body></html>", response.get("message-body"));
    }

    @Test public void FourOhFiveMethodNotAllowed() {
        request.put("Request-URI", "/file1");
        request.put("Method", "PUT");
        response = router.route(request);
        assertEquals("HTTP/1.0 405 Method Not Allowed", response.get("status-line"));
    }

    @Test public void postRequestWithoutBody() {
        request.put("Request-URI", "/file1");
        request.put("Method", "POST");
        request.remove("Body");
        response = router.route(request);
        assertEquals("<html><head><title></title></head><body> Empty </body></html>", response.get("message-body"));
    }

    @Test public void textFileIsFourOhFive() {
        request.put("Request-URI", "/text-file.txt");
        request.put("Method", "PUT");
        request.remove("Body");
        response = router.route(request);
        assertEquals("<html><head><title></title></head><body> Empty </body></html>", response.get("message-body"));
    }
}
